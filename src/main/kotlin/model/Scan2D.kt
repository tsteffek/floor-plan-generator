package model

import io.Reader
import mu.KotlinLogging
import java.io.File
import kotlin.math.cos
import kotlin.math.sin

val idKey = "id"
val stepSizeKey = "step size"
val distanceKey = "distance"
val qualityKey = "quality"

private val logger by lazy { KotlinLogging.logger {} }

/**
 * Expects a list of angle_since_start, length, quality
 */
class Scan2D(val rawData: List<ScanData>, private val scanner: Scanner) {
    val pointCloud = calculatePointCloud(rawData, scanner)

    private fun calculatePointCloud(rawData: List<ScanData>, scanner: Scanner): List<Point> {
        logger.debug { "Calculating point cloud from ${rawData.size} data points." }
        return (if (scanner.incremental) toTotalStepSizes(rawData) else rawData)
            .map {
                val clockwise = if (scanner.clockwise) -1 else 1
                Point(
                    rotateFromStart(it.stepSize * scanner.stepAngle * clockwise),
                    it.distance,
                    it.quality
                )
            }
    }

    private fun toTotalStepSizes(rawData: List<ScanData>): List<ScanData> {
        var stepSizeSum = 0.0
        return rawData
            .sortedByDescending { it.id }
            .map {
                stepSizeSum += it.stepSize
                ScanData(it.id, stepSizeSum, it.distance, it.quality)
            }
    }

    private fun rotateBy(vec: Pair<Double, Double>, angle: Double): Pair<Double, Double> {
        val radians = Math.toRadians(angle)
        val x2 = cos(radians) * vec.first - sin(radians) * vec.second
        val y2 = sin(radians) * vec.first + cos(radians) * vec.second
        return Pair(x2, y2)
    }

    private fun rotateFromStart(angle: Double): Pair<Double, Double> = rotateBy(Pair(0.0, 1.0), angle)

    fun toTSV(): String {
        val sb = StringBuilder()
        sb.appendln(scanner.toString())
        sb.appendln("x\ty\tquality")
        pointCloud.forEach { sb.appendln(it.toTSVString()) }
        return sb.toString()
    }

    data class ScanData(
        val id: Int,
        val stepSize: Double,
        val distance: Double,
        val quality: Int
    )

    companion object {
        fun fromTSV(tsv: File, scanner: Scanner): Scan2D {
            logger.info { "Reading from file $tsv..." }
            return fromData(Reader.fromTSV(tsv), scanner)
        }

        fun fromTSV(tsv: String, scanner: Scanner): Scan2D {
            logger.debug { "Reading TSV: \n$tsv" }
            return fromData(Reader.fromTSV(tsv), scanner)
        }

        fun fromData(data: List<Map<String, String>>, scanner: Scanner): Scan2D {
            val rawData = data
                .mapIndexed { index, it ->
                    ScanData(
                        it[idKey]?.toInt() ?: error("$idKey missing in CSV line ${index + 1}"),
                        it[stepSizeKey]?.toDouble() ?: error("$stepSizeKey missing in CSV line ${index + 1}"),
                        it[distanceKey]?.toDouble() ?: error("$distanceKey missing in CSV line ${index + 1}"),
                        it[qualityKey]?.toInt() ?: error("$qualityKey missing in CSV line ${index + 1}")
                    )
                }

            return Scan2D(rawData, scanner)
        }
    }
}
