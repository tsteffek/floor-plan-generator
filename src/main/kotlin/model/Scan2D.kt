package model

import io.readFromTSV
import model.geometry.PolarPoint
import mu.KotlinLogging
import java.io.File
import java.util.concurrent.atomic.AtomicInteger

private val logger by lazy { KotlinLogging.logger {} }

class Scan2D(val pointCloud: List<PolarPoint>, private val scanner: Scanner) {

    fun rotateBy(angle: Double): List<PolarPoint> =
        pointCloud.map { it.rotateBy(angle) }

    fun toTSV(): String {
        val sb = StringBuilder()
        sb.appendln(scanner.toString())
        sb.appendln("distance\tangle\tquality")
        pointCloud.forEach { sb.appendln(it.toTSVString()) }
        return sb.toString()
    }

    companion object {

        data class ScanData(
            val id: Int,
            val amountOfSteps: Double,
            val distance: Double?,
            val quality: Int?
        )

        fun fromTSV(tsvFile: File, scanner: Scanner): Scan2D {
            logger.info { "Reading from file $tsvFile..." }
            return fromMap(readFromTSV(tsvFile), scanner)
        }

        fun fromTSV(tsvString: String, scanner: Scanner): Scan2D {
            logger.debug { "Reading TSV: \n$tsvString" }
            return fromMap(readFromTSV(tsvString), scanner)
        }

        private fun fromMap(map: List<Map<String, String>>, scanner: Scanner): Scan2D {
            var parsedData = map
                .mapIndexed { index, dataObject ->
                    val id = dataObject[scanner.idKey]?.toInt()
                    val amountOfSteps = dataObject[scanner.stepSizeKey]?.toDoubleOrNull()
                    val distance = dataObject[scanner.distanceKey]?.toDoubleOrNull()
                    val quality = dataObject[scanner.qualityKey]?.toIntOrNull()

                    requireNotNull(id)
                    { "${scanner.idKey} missing in CSV line ${index + 1}" }
                    require(amountOfSteps != null && !amountOfSteps.isNaN())
                    { "${scanner.stepSizeKey} missing in CSV line ${index + 1}" }

                    ScanData(id, amountOfSteps, distance, quality)
                }

            if (scanner.incremental) parsedData = toTotalStepSizes(parsedData)

            val points = calculatePoints(parsedData, scanner)
            return Scan2D(points, scanner)
        }


        private fun calculatePoints(data: List<ScanData>, scanner: Scanner)
                : List<PolarPoint> {
            val countNull = AtomicInteger(0)
            val countQuality = AtomicInteger(0)

            val points = data
                .asSequence()
                .filterAndCount(countNull) { it.distance !== null && !it.distance.isNaN() && it.quality !== null }
                .filterAndCount(countQuality) { it.quality!! < scanner.qualityMax }
                .map {
                    val clockwise = if (scanner.clockwise) -1 else 1
                    val angleInRad = Math.toRadians(it.amountOfSteps * scanner.stepAngle * clockwise)
                    PolarPoint(angleInRad, it.distance!!, it.quality!!)
                }.toList()

            logger.info {
                "skipped points: $countNull [NaN], " +
                        "$countQuality [quality > ${scanner.qualityMax}] " +
                        "out of ${data.size}"
            }
            return points
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
    }
}
