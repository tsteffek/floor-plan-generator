@file:JvmName("Utils")
@file:JvmMultifileClass

package model

import io.Reader
import mu.KotlinLogging
import java.io.File
import java.util.concurrent.atomic.AtomicInteger

val idKey = "id"
val stepSizeKey = "step size"
val distanceKey = "distance"
val qualityKey = "quality"

private val logger by lazy { KotlinLogging.logger {} }

inline fun <T> Sequence<T>.filterAndCount(count: AtomicInteger, crossinline predicate: (T) -> Boolean): Sequence<T> =
    this.filter {
        if (predicate(it))
            true
        else {
            count.incrementAndGet()
            false
        }
    }

/**
 * Expects a list of angle_since_start, length, quality
 */
class Scan2D(val rawData: List<ScanData>, private val scanner: Scanner) {
    val pointCloud = calculatePointCloud(rawData, scanner)

    private fun calculatePointCloud(rawData: List<ScanData>, scanner: Scanner): List<Point> {
        logger.debug { "Calculating point cloud from ${rawData.size} data points." }
        val countNull = AtomicInteger(0)
        val countQuality = AtomicInteger(0)

        val pointCloud = (if (scanner.incremental) toTotalStepSizes(rawData) else rawData)
            .asSequence()
            .filterAndCount(countNull) { it.distance !== null && it.quality !== null }
            .filterAndCount(countQuality) { it.quality!! < scanner.qualityMax }
            .map {
                val clockwise = if (scanner.clockwise) -1 else 1
                Point.fromPolarCoordinates(
                    it.distance!!,
                    it.stepSize * scanner.stepAngle * clockwise,
                    it.quality!!
                )
            }.toList()
        logger.info { "skipped points: $countNull [NaN], $countQuality [quality > ${scanner.qualityMax}]." }
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

    fun rotateBy(angle: Double): List<Point> =
        pointCloud.map { it.rotateBy(angle) }

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

        private fun fromData(data: List<Map<String, String>>, scanner: Scanner): Scan2D {
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
