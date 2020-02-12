package de.tsteffek.model.geometry

import kotlin.math.cos
import kotlin.math.sin

/**
 * The [GeometricObject] resembling a 2D point expressed in polar coordinates.
 * @property angle the angle creating a line from the origin
 * @property distance the distance along the line until this point.
 * @property quality the quality of the measurement taken to produce this point
 * (makes only sense for points coming from a lidar scanner)
 */
class PolarPoint(
    val angle: Double,
    val distance: Double,
    val quality: Int
) : Point(distance * cos(angle), distance * sin(angle)) {

    constructor(angle: Int, distance: Double, quality: Int): this(angle.toDouble(), distance, quality)
    constructor(angle: Double, distance: Int, quality: Int): this(angle, distance.toDouble(), quality)
    constructor(angle: Int, distance: Int, quality: Int): this(angle.toDouble(), distance.toDouble(), quality)

    /** The vector this point creates, but with length of 1. */
    fun normalizedDirection(): Pair<Double, Double> {
        val length = distance
        return Pair(x / length, y / length)
    }

    /** Rotates this [PolarPoint] along the origin. */
    fun rotateBy(angle: Double): PolarPoint =
        PolarPoint(this.angle + angle, distance, quality)

    override fun toTSVString(): String =
        "$distance\t$angle\t$quality"

    override fun toString(): String =
        "Point(distance=$distance, angle=$angle, quality=$quality)"
}
