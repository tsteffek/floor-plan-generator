package de.tsteffek.model

import de.tsteffek.io.readFromTSV
import de.tsteffek.model.geometry.PolarPoint
import mu.KotlinLogging
import java.io.File
import java.util.concurrent.atomic.LongAdder

private val logger by lazy { KotlinLogging.logger {} }

/**
 * An object containing the results of a radial 2D lidar scan.
 * @property pointCloud a [List] of detected [PolarPoint]s
 * @property scanner the [Scanner] used to create the data
 */
class Scan2D(val pointCloud: List<PolarPoint>, private val scanner: Scanner) {

    /** Rotates all the points by [angle]. */
    fun rotateBy(angle: Double): List<PolarPoint> =
        pointCloud.map { it.rotateBy(angle) }

    /** Returns this object as TSV [String]. */
    fun toTSV(): String {
        val sb = StringBuilder()
        sb.appendln(scanner.toString())
        sb.appendln("distance\tangle\tquality")
        pointCloud.forEach { sb.appendln(it.toTSVString()) }
        return sb.toString()
    }

    companion object {

        private data class ScanData(
            val id: Int,
            val amountOfSteps: Double,
            val distance: Double?,
            val quality: Int?
        )

        /**
         * Creates a [Scan2D] from a tsv [File].
         *
         * Assumes a header line with the values matching the keys in the
         * scanner.
         * @param scanner the [Scanner] used to create the data
         */
        fun fromTSV(tsvFile: File, scanner: Scanner): Scan2D {
            logger.info { "Reading from file $tsvFile..." }
            return fromMap(readFromTSV(tsvFile), scanner)
        }

        /**
         * Creates a [Scan2D] from a [String] leading to a tsv file
         *
         * Assumes a header line with the values matching the keys in the
         * scanner.
         * @param scanner the [Scanner] used to create the data
         */
        fun fromTSV(tsvString: String, scanner: Scanner): Scan2D {
            logger.debug { "Reading TSV: \n$tsvString" }
            return fromMap(readFromTSV(tsvString), scanner)
        }

        private fun fromMap(map: List<Map<String, String>>, scanner: Scanner): Scan2D {
            val parsedData = parseToScanData(map, scanner.tsvKeys, scanner.incremental)
            val points = calculatePoints(parsedData, scanner)
            return Scan2D(points, scanner)
        }

        private fun parseToScanData(
            map: List<Map<String, String>>,
            tsvKeys: TsvKeys,
            incremental: Boolean
        ): List<ScanData> {
            val parsedData = map
                .mapIndexed { index, dataObject ->
                    val id = dataObject[tsvKeys.idKey]?.toInt()
                    val amountOfSteps = dataObject[tsvKeys.stepSizeKey]?.toDoubleOrNull()
                    val distance = dataObject[tsvKeys.distanceKey]?.toDoubleOrNull()
                    val quality = dataObject[tsvKeys.qualityKey]?.toIntOrNull()

                    requireNotNull(id)
                    { "${tsvKeys.idKey} missing in TSV line ${index + 1}" }
                    require(amountOfSteps != null && !amountOfSteps.isNaN())
                    { "${tsvKeys.stepSizeKey} missing in TSV line ${index + 1}" }

                    ScanData(id, amountOfSteps, distance, quality)
                }
            return if (!incremental) parsedData
            else toTotalStepSizes(parsedData)
        }

        private fun toTotalStepSizes(rawData: List<ScanData>): List<ScanData> {
            var stepSizeSum = 0.0
            return rawData
                .sortedByDescending { it.id }
                .map {
                    stepSizeSum += it.amountOfSteps
                    ScanData(it.id, stepSizeSum, it.distance, it.quality)
                }
        }

        private fun calculatePoints(data: List<ScanData>, scanner: Scanner)
                : List<PolarPoint> {
            val counterNull = LongAdder()
            val counterQuality = LongAdder()

            val nullCountFilter = filterAndCount<ScanData>(counterNull) {
                it.distance !== null && !it.distance.isNaN() && it.quality !== null
            }
            val qualityCountFilter = filterAndCount<ScanData>(counterQuality) {
                it.quality!! < scanner.qualityMax
            }

            val points = data
                .asSequence()
                .filter(nullCountFilter)
                .filter(qualityCountFilter)
//                .filterAndCount(countNull) { it.distance !== null && !it.distance.isNaN() && it.quality !== null }
//                .filterAndCount(countQuality) { it.quality!! < scanner.qualityMax }
                .map {
                    val clockwise = if (scanner.clockwise) -1 else 1
                    val angleInRad = Math.toRadians(it.amountOfSteps * scanner.stepAngle * clockwise)
                    PolarPoint(angleInRad, it.distance!!, it.quality!!)
                }.toList()

            logger.info {
                "skipped points: ${counterNull.sum()} [NaN]," +
                        " ${counterQuality.sum()} [quality > ${scanner.qualityMax}] out of ${data.size}"
            }
            return points
        }
    }
}
