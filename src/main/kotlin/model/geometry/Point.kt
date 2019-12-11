package model.geometry

import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

fun distancePointToPoint(a: Point, b: Point) =
    lengthOf(a.x - b.x, a.y - b.y)

fun lengthOf(x: Double, y: Double): Double =
    sqrt(x * x + y * y)

class Point(
    val angle: Double,
    val distance: Double,
    val quality: Int
) : GeometricObject<Point> {
    val x: Double = distance * cos(angle)
    val y: Double = distance * sin(angle)

    constructor(angle: Int, distance: Double, quality: Int): this(angle.toDouble(), distance, quality)
    constructor(angle: Double, distance: Int, quality: Int): this(angle, distance.toDouble(), quality)
    constructor(angle: Int, distance: Int, quality: Int): this(angle.toDouble(), distance.toDouble(), quality)

    override fun distanceTo(other: Point): Double =
        distancePointToPoint(this, other)

    fun normalizedDirection(): Pair<Double, Double> {
        val length = distance
        return Pair(x / length, y / length)
    }

    fun rotateBy(angle: Double): Point =
        Point(this.angle + angle, distance, quality)

    override fun toTSVString(): String =
        "$distance\t$angle\t$quality"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Point

        if (quality != other.quality) return false
        if (other.x - PRECISION > x || x > other.x + PRECISION) return false
        if (other.y - PRECISION > y || y > other.y + PRECISION) return false

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

    override fun toString(): String =
        "Point(distance=$distance, angle=$angle, quality=$quality)"
}
