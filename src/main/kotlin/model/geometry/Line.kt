package model.geometry

import math.PRECISION
import math.distanceLineToPoint
import model.mean
import kotlin.math.pow

/**
 * The [GeometricObject] resembling a simple line in slope-intercept-form.
 * @property slope the slope of the line, i.e. the increase along the y-axis per step along the x-axis.
 * @property intercept y-value of the interception point of this line and the y-axis
 */
class Line(val slope: Double, val intercept: Double) : GeometricObject {

    constructor(slope: Int, intercept: Double) : this(slope.toDouble(), intercept)
    constructor(slope: Double, intercept: Int) : this(slope, intercept.toDouble())
    constructor(slope: Int, intercept: Int) : this(slope.toDouble(), intercept.toDouble())

    override fun distanceTo(other: GeometricObject): Double =
        when (other) {
            is Point -> distanceLineToPoint(this, other)
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
        /** Computes the line between two [Point]s [a] and [b]. */
        fun fromTwoPoints(a: Point, b: Point): Line =
            fromSlopeAndPoint((a.y - b.y) / (a.x - b.x), a)

        /** Computes the line using a starting [point] and a [slope] */
        internal fun fromSlopeAndPoint(slope: Double, point: Point): Line =
            fromSlopeAndPoint(slope, point.x, point.y)

        private fun fromSlopeAndPoint(slope: Double, x: Double, y: Double): Line =
            Line(slope, y - slope * x)

        /**
         * Fits a line between several [points].
         * Algorithm used: Least Squares following https://www.varsitytutors.com/hotmath/hotmath_help/topics/line-of-best-fit
         */
        fun fromSeveralPoints(points: Collection<Point>): Line {
            val xMean = points.map { it.x }.mean()
            val yMean = points.map { it.y }.mean()
            val slope =
                points.map { (it.x - xMean) * (it.y - yMean) }.sum() / points.map { (it.x - xMean).pow(2) }.sum()

            return fromSlopeAndPoint(slope, xMean, yMean)
        }
    }
}
