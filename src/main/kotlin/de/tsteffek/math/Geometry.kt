package de.tsteffek.math

import de.tsteffek.model.geometry.Line
import de.tsteffek.model.geometry.LineSegment
import de.tsteffek.model.geometry.Point
import de.tsteffek.model.geometry.PolarPoint
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

/** Precomputed value of PI / 2 for performance reasons. */
const val PIHALF = PI / 2
/** Precision used for various geometric similarity checks. */
const val PRECISION = 1e-8

fun dot(a: Point, b: Point): Double =
    a.x * b.x + a.y * b.y

/**
 * Calculates the length of a 2-dimensional vector [[x], [y]].
 */
private fun lengthOf(x: Double, y: Double): Double =
    sqrt(lengthSquared(x, y))

private fun lengthSquared(x: Double, y: Double): Double =
    x * x + y * y

/**
 * Calculates the distance between two [Point]s [a] and [b].
 */
fun distance(a: Point, b: Point) =
    lengthOf(a.x - b.x, a.y - b.y)

/**
 * Calculates the shortest distance between a [Point] [p] and a [Line] [l].
 */
fun distance(p: Point, l: Line): Double {
    if (l.slope == 0.0) return l.intercept - p.y
    val inverseSlope = -1 / l.slope
    val perpendicularLine = Line.fromSlopeAndPoint(inverseSlope, p)
    val intersection = intersectTwoLines(l, perpendicularLine)!!
    return distance(p, intersection)
}

/**
 * Calculates the [Point] of intersection between two [Line]s [a] and [b].
 */
private fun intersectTwoLines(a: Line, b: Line): Point? {
    val slopeDif = a.slope - b.slope
    if (slopeDif == 0.0) return null
    val x = (b.intercept - a.intercept) / slopeDif
    val y = a.slope * x + a.intercept
    return Point(x, y)
}

/**
 * Calculates the distance between a [Point] [p] and a [LineSegment] [lineSeg].
 */
fun distance(p: Point, lineSeg: LineSegment): Double =
    distance(p, projectOntoSegment(p, lineSeg))

private fun projectOntoSegment(p: Point, lineSeg: LineSegment): Point {
    val t = max(0.0, min(1.0, lineVectorConstantToProjection(p, lineSeg.startPoint, lineSeg.endPoint)))
    return lineSeg.startPoint + (lineSeg.endPoint - lineSeg.startPoint) * t
}

internal fun projectOntoLine(p: Point, line: Line): Point {
    val startPoint = Point(0, line.intercept)
    val endPoint = Point(1, line.slope + line.intercept)
    val t = lineVectorConstantToProjection(p, startPoint, endPoint)
    return startPoint + (endPoint - startPoint) * t
}

private fun lineVectorConstantToProjection(p: Point, startPoint: Point, endPoint: Point): Double {
    val lineVec = endPoint - startPoint
    val length2 = lengthSquared(lineVec.x, lineVec.y)
    return dot(p - startPoint, lineVec) / length2
}

/**
 * Calculates the shortest distance between a line going through the origin and
 * [PolarPoint] [p].
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
 * Calculates the shortest distance between two [LineSegment]s [lineA] and
 * [lineB].
 */
fun distance(lineA: LineSegment, lineB: LineSegment): Double {
    val intersection = intersectTwoLines(lineA, lineB)
    if (intersection != null && intersection.liesOn(lineA) && intersection.liesOn(lineB)) return 0.0

    val distA1 = distance(lineA.startPoint, lineB)
    val distA2 = distance(lineA.endPoint, lineB)
    val distB1 = distance(lineB.startPoint, lineA)
    val distB2 = distance(lineB.endPoint, lineA)

    return min(min(distA1, distA2), min(distB1, distB2))
}
