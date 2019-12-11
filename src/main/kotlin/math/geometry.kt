package math

import model.geometry.Line
import model.geometry.Point
import model.geometry.PolarPoint
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sqrt

const val PIHALF = PI / 2
const val PRECISION = 1e-8

fun lengthOf(x: Double, y: Double): Double =
    sqrt(x * x + y * y)

fun distancePointToPoint(a: Point, b: Point) =
    lengthOf(a.x - b.x, a.y - b.y)

fun distanceOriginLineToPoint(angle: Double, p: PolarPoint): Double {
    val angleBetween = angle - p.angle
    return if (abs(angleBetween) < PIHALF)
        p.distance * cos(angleBetween)
    else
        p.distance
}

fun intersectTwoLines(a: Line, b: Line): Point {
    val x = (b.intercept - a.intercept) / (a.slope - b.slope)
    val y = a.slope * x + a.intercept
    return Point(x, y)
}

fun distanceLineToPoint(l: Line, p: Point): Double {
    val inverseSlope = - 1 / l.slope
    val perpendicularLine = Line.fromSlopeAndPoint(inverseSlope, p)
    val intersection = intersectTwoLines(l, perpendicularLine)
    return distancePointToPoint(p, intersection)
}
