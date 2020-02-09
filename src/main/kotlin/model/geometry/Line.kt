package model.geometry

import math.PRECISION
import math.distance
import model.mean
import kotlin.math.pow

open class Line(val slope: Double, val intercept: Double) : GeometricObject {

    constructor(slope: Int, intercept: Double) : this(slope.toDouble(), intercept)
    constructor(slope: Double, intercept: Int) : this(slope, intercept.toDouble())
    constructor(slope: Int, intercept: Int) : this(slope.toDouble(), intercept.toDouble())
    constructor(slopeAndIntercept: Pair<Double, Double>) : this(slopeAndIntercept.first, slopeAndIntercept.second)

    override fun distanceTo(other: GeometricObject): Double =
        when (other) {
            is Point -> distance(other, this)
            is Line ->
                if (other.slope != slope) 0.0
                else throw NotImplementedError()
            else -> throw NotImplementedError()
        }

    override fun toTSVString(): String =
        "$slope\t$intercept"

    override fun toString(): String =
        "Line(slope=$slope, intercept=$intercept)"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Line

        if (other.slope - PRECISION > slope || slope > other.slope + PRECISION) return false
        if (other.intercept - PRECISION > intercept || intercept > other.intercept + PRECISION) return false

        return true
    }

    override fun hashCode(): Int {
        var result = slope.hashCode()
        result = 31 * result + intercept.hashCode()
        return result
    }

    companion object {
        fun fromTwoPoints(a: Point, b: Point): Line =
            fromSlopeAndPoint(slopeBetweenTwoPoints(a, b), a)

        private fun slopeBetweenTwoPoints(a: Point, b: Point): Double =
            (a.y - b.y) / (a.x - b.x)

        internal fun fromSlopeAndPoint(slope: Double, a: Point): Line =
            Line(slope, interceptFromPointWithSlope(slope, a.x, a.y))

        private fun interceptFromPointWithSlope(slope: Double, x: Double, y: Double): Double =
            y - slope * x

        /**
         * Least Squares following https://www.varsitytutors.com/hotmath/hotmath_help/topics/line-of-best-fit
         */
        fun fromSeveralPoints(points: Collection<Point>): Line {
            val xMean = points.map { it.x }.mean()
            val yMean = points.map { it.y }.mean()

            val slope = points.map { (it.x - xMean) * (it.y - yMean) }.sum() /
                    points.map { (it.x - xMean).pow(2) }.sum()
            val intercept = interceptFromPointWithSlope(slope, xMean, yMean)

            return Line(slope, intercept)
        }
    }
}
