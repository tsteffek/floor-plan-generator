package model.geometry

import kotlin.math.abs
import kotlin.math.cos

fun distanceOriginLineToPoint(l: Line, p: Point) =
    distanceOriginLineToPoint(l.angle, p)

fun distanceOriginLineToPoint(angle: Double, p: Point): Double {
    val maxPossibleAngle = 90
    val angleBetween = angle - p.angle
    return if (abs(angleBetween) < maxPossibleAngle)
        p.distance * cos(Math.toRadians(angleBetween))
    else
        p.distance
}

data class Line(val angle: Double, val constant: Double)
