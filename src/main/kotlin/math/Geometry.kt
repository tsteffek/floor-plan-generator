package math

import model.geometry.Line
import model.geometry.Point
import model.geometry.PolarPoint
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sqrt

/** Precomputed value of PI / 2 for performance reasons. */
const val PIHALF = PI / 2
/** Precision used for various geometric similarity checks. */
const val PRECISION = 1e-8

/**
 * Calculates the length of a 2-dimensional vector [[x], [y]].
 */
fun lengthOf(x: Double, y: Double): Double =
    sqrt(x * x + y * y)

/**
 * Calculates the distance between two [Point]s [a] and [b].
 */
fun distancePointToPoint(a: Point, b: Point) =
    lengthOf(a.x - b.x, a.y - b.y)

/**
 * Calculates the shortest distance between a line going through the origin and [PolarPoint] [p].
 * @param angle the angle of the line, with 0 being a line pointing to the right
 */
fun distanceOriginLineToPoint(angle: Double, p: PolarPoint): Double {
    val angleBetween = angle - p.angle
    return if (abs(angleBetween) < PIHALF)
        p.distance * cos(angleBetween)
    else
        p.distance
}

/**
 * Calculates the [Point] of intersection between two [Line]s [a] and [b].
 */
fun intersectTwoLines(a: Line, b: Line): Point {
    val x = (b.intercept - a.intercept) / (a.slope - b.slope)
    val y = a.slope * x + a.intercept
    return Point(x, y)
}

/**
 * Calculates the shortest distance between a [Line] [l] and a [Point] [p].
 */
fun distanceLineToPoint(l: Line, p: Point): Double {
    if (l.slope == 0.0) return l.intercept - p.y
    val inverseSlope = -1 / l.slope
    val perpendicularLine = Line.fromSlopeAndPoint(inverseSlope, p)
    val intersection = intersectTwoLines(l, perpendicularLine)
    return distancePointToPoint(p, intersection)
}
