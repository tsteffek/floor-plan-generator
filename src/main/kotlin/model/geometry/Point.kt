package model.geometry

import math.PRECISION
import math.distance

open class Point(val x: Double, val y: Double) : GeometricObject {

    constructor(x: Int, y: Double) : this(x.toDouble(), y)
    constructor(x: Double, y: Int) : this(x, y.toDouble())
    constructor(x: Int, y: Int) : this(x.toDouble(), y.toDouble())

    operator fun plus(other: Point): Point =
        Point(x + other.x, y + other.y)

    operator fun minus(other: Point): Point =
        Point(x - other.x, y - other.y)

    operator fun times(constant: Double): Point =
        Point(x * constant, y * constant)

    fun liesOn(line: Line): Boolean =
        line.slope * x + line.intercept == y

    fun liesOn(lineSeg: LineSegment): Boolean {
        val doesntLieOnLine = !liesOn(lineSeg as Line)
        val oneOfThePoints = lineSeg.startPoint == this || lineSeg.endPoint == this
        return if (doesntLieOnLine) false
        else if (oneOfThePoints) true
        else lineSeg.inBoundingBox(this)
    }

    override fun distanceTo(other: GeometricObject): Double =
        when (other) {
            is Point -> distance(other, this)
            is Line -> distance(this, other)
            else -> throw NotImplementedError()
        }

    override fun toTSVString(): String =
        "$x\t$y"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Point

        if (other.x - PRECISION > x || x > other.x + PRECISION) return false
        if (other.y - PRECISION > y || y > other.y + PRECISION) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x.hashCode()
        result = 31 * result + y.hashCode()
        return result
    }

    override fun toString(): String =
        "Point(x=$x, y=$y)"
}
