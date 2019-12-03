package model.geometry

import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

fun distancePointToPoint(a: Point, b: Point) =
    lengthOf(a.x - b.x, a.y - b.y)

fun lengthOf(x: Double, y: Double): Double =
    sqrt(x * x + y * y)

data class Point(
    val distance: Double,
    val angle: Double,
    val quality: Int
) : GeometricObject<Point> {
    val x: Double
    val y: Double

    init {
        val rad = Math.toRadians(angle)
        x = distance * cos(rad)
        y = distance * sin(rad)
    }

    override fun distanceTo(other: Point): Double =
        distancePointToPoint(this, other)

    fun normalizedDirection(): Pair<Double, Double> {
        val length = distance
        return Pair(x / length, y / length)
    }

    fun rotateBy(angle: Double): Point =
        Point(distance, this.angle + angle, quality)

    override fun toTSVString(): String =
        "$distance\t$angle\t$quality"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Point

        if (quality != other.quality) return false
        if (other.x - 1e-8 > x || x > other.x + 1e-8) return false
        if (other.y - 1e-8 > y || y > other.y + 1e-8) return false

        return true
    }

    override fun hashCode(): Int {
        var result = distance.hashCode()
        result = 31 * result + angle.hashCode()
        result = 31 * result + quality
        result = 31 * result + x.hashCode()
        result = 31 * result + y.hashCode()
        return result
    }
}
