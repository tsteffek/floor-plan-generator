package model.geometry

import maths.distanceLineToPoint
import kotlin.math.*

const val piHalf = PI / 2

data class Line(val slope: Double, val intercept: Double) : GeometricObject {

    constructor(slope: Int, intercept: Double) : this(slope.toDouble(), intercept)
    constructor(slope: Double, intercept: Int) : this(slope, intercept.toDouble())
    constructor(slope: Int, intercept: Int) : this(slope.toDouble(), intercept.toDouble())

    override fun distanceTo(other: GeometricObject): Double =
        when (other) {
            is Point -> distanceLineToPoint(this, other)
            is Line -> 0.0
            else -> throw NotImplementedError()
        }

    override fun toTSVString(): String =
        "$slope\t$intercept"

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
            fromSlopeAndPoint((a.y - b.y) / (a.x - b.x), a)

        fun fromSlopeAndPoint(slope: Double, a: Point): Line =
            Line(slope, a.y - slope * a.x)
    }
}
