package de.tsteffek.model.geometry

import de.tsteffek.math.PRECISION
import de.tsteffek.math.distanceLineToPoint
import de.tsteffek.math.distancePointToPoint

/**
 * The [GeometricObject] resembling a simple 2D point.
 * @property x location on the x-axis
 * @property y location on the y-axis
 */
open class Point(val x: Double, val y: Double) : GeometricObject {

    constructor(x: Int, y: Double) : this(x.toDouble(), y)
    constructor(x: Double, y: Int) : this(x, y.toDouble())
    constructor(x: Int, y: Int) : this(x.toDouble(), y.toDouble())

    override fun distanceTo(other: GeometricObject): Double =
        when (other) {
            is Point -> distancePointToPoint(
                other,
                this
            )
            is Line -> distanceLineToPoint(
                other,
                this
            )
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
