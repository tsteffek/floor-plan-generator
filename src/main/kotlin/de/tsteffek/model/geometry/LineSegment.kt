package de.tsteffek.model.geometry

import de.tsteffek.math.distance
import de.tsteffek.math.projectOntoLine

/**
 * The [GeometricObject] resembling a line segment in slope-intercept-form,
 * with a start and end point.
 * @property startPoint the startPoint
 * @property endPoint the endPoint
 */
class LineSegment(line: Line, val startPoint: Point, val endPoint: Point) :
    Line(line.slope, line.intercept) {

    constructor(startPoint: Point, endPoint: Point)
            : this(fromTwoPoints(startPoint, endPoint), startPoint, endPoint)

    override fun distanceTo(other: GeometricObject): Double =
        when (other) {
            is Point -> distance(other, this)
            is LineSegment -> distance(this, other)
            is Line ->
                if (other.slope != slope) 0.0
                else throw NotImplementedError()
            else -> throw NotImplementedError()
        }

    internal fun inBoundingBox(p: Point): Boolean {
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
        /**
         * Fits a line between several [PolarPoint]s.
         * Utilizes the natural ordering of PolarPoints to determine ending and starting point.
         * Algorithm used: Least Squares following
         * https://www.varsitytutors.com/hotmath/hotmath_help/topics/line-of-best-fit
         */
        fun fromSeveralPoints(points: Collection<PolarPoint>): LineSegment {
            val line = Line.fromSeveralPoints(points)
            val sortedPoints = points.sortedWith(compareBy(PolarPoint::angle, PolarPoint::distance))
            val startPoint = projectOntoLine(sortedPoints.first(), line)
            val endPoint = projectOntoLine(sortedPoints.last(), line)
            return LineSegment(line, startPoint, endPoint)
        }
    }
}
