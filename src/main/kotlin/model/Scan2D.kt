@file:JvmName("Utils")
@file:JvmMultifileClass

package model

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import model.geometry.Point
import mu.KotlinLogging
import java.io.File
import java.util.concurrent.atomic.AtomicInteger

private val logger by lazy { KotlinLogging.logger {} }

private fun getTSVReader() = csvReader {
    charset = "UTF-8"
    quoteChar = '"'
    delimiter = '\t'
    escapeChar = '\\'
}

fun readFromTSV(tsv: File): List<Map<String, String>> =
    getTSVReader().readAllWithHeader(tsv)

fun readFromTSV(tsv: String): List<Map<String, String>> =
    getTSVReader().readAllWithHeader(tsv)

class Scan2D(val pointCloud: List<Point>, private val scanner: Scanner) {

    fun rotateBy(angle: Double): List<Point> =
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
                    ScanData(
                        dataObject[scanner.idKey]?.toInt()
                            ?: error("${scanner.idKey} missing in CSV line ${index + 1}"),
                        dataObject[scanner.stepSizeKey]?.toDouble()
                            ?: error("${scanner.stepSizeKey} missing in CSV line ${index + 1}"),
                        dataObject[scanner.distanceKey]?.toDoubleOrNull(),
                        dataObject[scanner.qualityKey]?.toIntOrNull()
                    )
                }

            if (scanner.incremental)
                parsedData = toTotalStepSizes(parsedData)

            val points = calculatePoints(parsedData, scanner)

            return Scan2D(points, scanner)
        }


        private fun calculatePoints(data: List<ScanData>, scanner: Scanner)
                : List<Point> {
            val countNull = AtomicInteger(0)
            val countQuality = AtomicInteger(0)

            val points = data
                .asSequence()
                .filterAndCount(countNull) { it.distance !== null && it.quality !== null }
                .filterAndCount(countQuality) { it.quality!! < scanner.qualityMax }
                .map {
                    val clockwise = if (scanner.clockwise) -1 else 1
                    val angleInRad = Math.toRadians(it.amountOfSteps * scanner.stepAngle * clockwise)
                    Point(angleInRad, it.distance!!, it.quality!!)
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
