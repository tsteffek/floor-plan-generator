package model.geometry

import math.distance

class LineSegment(val startPoint: Point, val endPoint: Point) :
    Line(slopeAndInterceptFromTwoPoints(startPoint, endPoint)) {

    override fun distanceTo(other: GeometricObject): Double =
        when (other) {
            is Point -> distance(other, this)
            is LineSegment -> distance(this, other)
            is Line ->
                if (other.slope != slope) 0.0
                else throw NotImplementedError()
            else -> throw NotImplementedError()
        }

    internal fun inBoundingBox(p:Point): Boolean {
        val xMax: Double
        val yMax: Double
        val xMin: Double
        val yMin: Double
        if (startPoint.x > endPoint.x) {
            xMax = startPoint.x
            xMin = endPoint.x
        } else {
            xMin = startPoint.x
            xMax = endPoint.x
        }
        if (startPoint.y > endPoint.y) {
            yMax = startPoint.y
            yMin = endPoint.y
        } else {
            yMin = startPoint.y
            yMax = endPoint.y
        }
        return xMin < p.x && p.x < xMax && yMin < p.y && p.y < yMax
    }

    companion object {
        private fun slopeAndInterceptFromTwoPoints(a: Point, b: Point): Pair<Double, Double> {
            val slope = slopeBetweenTwoPoints(a, b)
            val intercept = interceptFromPointWithSlope(slope, a.x, a.y)
            return Pair(slope, intercept)
        }
    }
}
