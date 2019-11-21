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

    fun rotate(angle: Double): List<Point> = pointCloud.map {
        Point(rotateVec(Pair(it.x, it.y), angle), 1.0, it.quality)
    }

    private fun calculatePointCloud(rawData: List<ScanData>, scanner: Scanner): List<Point> {
        logger.debug { "Calculating point cloud from ${rawData.size} data points." }
        var countNull = 0
        var countQuality = 0

        val pointCloud = (if (scanner.incremental) toTotalStepSizes(rawData) else rawData)
            .filter {
                if (it.distance !== null && it.quality !== null) {
                    countNull++
                    true
                } else false
            }
            .filter {
                if (it.quality!! < scanner.qualityMax) {
                    countQuality++
                    true
                } else false
            }
            .map {
                val clockwise = if (scanner.clockwise) -1 else 1
                Point(
                    rotateFromStart(it.stepSize * scanner.stepAngle * clockwise),
                    it.distance!!,
                    it.quality!!
                )
            }
        logger.trace { "skipped $countNull points because of NaN, $countQuality points because of quality above ${scanner.qualityMax}." }
        return pointCloud
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

    private fun rotateVec(vec: Pair<Double, Double>, angle: Double): Pair<Double, Double> {
        val radians = Math.toRadians(angle)
        val x2 = cos(radians) * vec.first - sin(radians) * vec.second
        val y2 = sin(radians) * vec.first + cos(radians) * vec.second
        return Pair(x2, y2)
    }

    private fun rotateFromStart(angle: Double): Pair<Double, Double> = rotateVec(Pair(0.0, 1.0), angle)

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
        val distance: Double?,
        val quality: Int?
    ) {

    }

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
                        it[distanceKey]?.toDoubleOrNull(),
                        it[qualityKey]?.toIntOrNull()
                    )
                }

            return Scan2D(rawData, scanner)
        }
    }
}
