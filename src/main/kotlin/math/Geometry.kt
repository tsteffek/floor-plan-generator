package math

import model.geometry.Line
import model.geometry.LineSegment
import model.geometry.Point
import model.geometry.PolarPoint
import kotlin.math.*

/** Precomputed value of PI / 2 for performance reasons. */
const val PIHALF = PI / 2
/** Precision used for various geometric similarity checks. */
const val PRECISION = 1e-8

fun dot(a: Point, b: Point): Double =
    a.x * b.x + a.y * b.y

private fun lengthOf(x: Double, y: Double): Double =
    sqrt(lengthSquared(x, y))

private fun lengthSquared(x: Double, y: Double): Double =
    x * x + y * y

fun distance(a: Point, b: Point) =
    lengthOf(a.x - b.x, a.y - b.y)

fun distance(p: Point, l: Line): Double {
    if (l.slope == 0.0) return l.intercept - p.y
    val inverseSlope = -1 / l.slope
    val perpendicularLine = Line.fromSlopeAndPoint(inverseSlope, p)
    val intersection = intersectTwoLines(l, perpendicularLine)!!
    return distance(p, intersection)
}

private fun intersectTwoLines(a: Line, b: Line): Point? {
    val slopeDif = a.slope - b.slope
    if (slopeDif == 0.0) return null
    val x = (b.intercept - a.intercept) / slopeDif
    val y = a.slope * x + a.intercept
    return Point(x, y)
}

fun distance(p: Point, lineSeg: LineSegment): Double {
    return distance(p, projectOntoSegment(p, lineSeg))
}

private fun projectOntoSegment(p: Point, lineSeg: LineSegment): Point {
    val lineVec = lineSeg.endPoint - lineSeg.startPoint
    val length2 = lengthSquared(lineVec.x, lineVec.y)
    val t = max(0.0, min(1.0, dot(p - lineSeg.startPoint, lineVec) / length2))
    return lineSeg.startPoint + (lineSeg.endPoint - lineSeg.startPoint) * t
}

fun distanceOriginLineToPoint(angle: Double, p: PolarPoint): Double {
    val angleBetween = angle - p.angle
    return if (abs(angleBetween) < PIHALF)
        p.distance * cos(angleBetween)
    else
        p.distance
}

fun distance(lineA: LineSegment, lineB: LineSegment): Double {
    val intersection = intersectTwoLines(lineA, lineB)
    if (intersection != null && intersection.liesOn(lineA) && intersection.liesOn(lineB)) return 0.0

    val distA1 = distance(lineA.startPoint, lineB)
    val distA2 = distance(lineA.endPoint, lineB)
    val distB1 = distance(lineB.startPoint, lineA)
    val distB2 = distance(lineB.endPoint, lineA)

    return min(min(distA1, distA2), min(distB1, distB2))
}
