package model.geometry

import kotlin.math.cos
import kotlin.math.sin

class PolarPoint(
    val angle: Double,
    val distance: Double,
    val quality: Int
) : Point(distance * cos(angle), distance * sin(angle)) {

    constructor(angle: Int, distance: Double, quality: Int): this(angle.toDouble(), distance, quality)
    constructor(angle: Double, distance: Int, quality: Int): this(angle, distance.toDouble(), quality)
    constructor(angle: Int, distance: Int, quality: Int): this(angle.toDouble(), distance.toDouble(), quality)

    fun normalizedDirection(): Pair<Double, Double> {
        val length = distance
        return Pair(x / length, y / length)
    }

    fun rotateBy(angle: Double): PolarPoint =
        PolarPoint(this.angle + angle, distance, quality)

    override fun toTSVString(): String =
        "$distance\t$angle\t$quality"

    override fun toString(): String =
        "Point(distance=$distance, angle=$angle, quality=$quality)"
}
