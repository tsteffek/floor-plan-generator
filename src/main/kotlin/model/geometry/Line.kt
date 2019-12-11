package model.geometry

import kotlin.math.*

const val piHalf = PI / 2

fun distanceOriginLineToPoint(angle: Double, p: Point): Double {
    val angleBetween = angle - p.angle
    return if (abs(angleBetween) < piHalf)
        p.distance * cos(angleBetween)
    else
        p.distance
}

fun distanceLineToPoint(l: Line, p: Point): Double {
    val beta = PI - l.angle
    val c = distancePointToPoint(p, l.basePoint())
    val b = c * sin(beta)
    return sqrt(c.pow(2) - b.pow(2))
}

data class Line(val angle: Double, val distance: Double) {

    constructor(angle: Int, distance: Double) : this(angle.toDouble(), distance)
    constructor(angle: Double, distance: Int) : this(angle, distance.toDouble())
    constructor(angle: Int, distance: Int) : this(angle.toDouble(), distance.toDouble())

    fun basePoint(): Point = Point(angle, distance, Int.MAX_VALUE)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Line

        if (other.angle - PRECISION > angle || angle > other.angle + PRECISION) return false
        if (other.distance - PRECISION > distance || distance > other.distance + PRECISION) return false

        return true
    }

    override fun hashCode(): Int {
        var result = angle.hashCode()
        result = 31 * result + distance.hashCode()
        return result
    }

    companion object {
        fun fromTwoPoints(a: Point, b: Point): Line {
            val alpha = if (a.x == b.x) piHalf else atan((b.y - a.y) / (b.x - a.x))
            val theta = piHalf - alpha // in range of 0 to pi
            val distance = a.distance * sin(a.angle - alpha)
            return if (distance >= 0) Line(theta, distance)
            else Line(2 * PI - theta, -distance)
        }
    }
}
