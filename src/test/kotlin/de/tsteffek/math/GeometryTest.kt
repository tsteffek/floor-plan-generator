package de.tsteffek.math

import io.kotlintest.data.suspend.forall
import io.kotlintest.matchers.doubles.plusOrMinus
import io.kotlintest.shouldBe
import io.kotlintest.specs.FreeSpec
import io.kotlintest.tables.row
import de.tsteffek.model.geometry.Line
import de.tsteffek.model.geometry.LineSegment
import de.tsteffek.model.geometry.Point
import de.tsteffek.model.geometry.PolarPoint
import kotlin.math.PI
import kotlin.math.sqrt

internal class GeometryTest : FreeSpec({

    "piHalf" {
        PIHALF shouldBe PI / 2
    }

    "distance PointToPoint" {
        val pointA = PolarPoint(Math.toRadians(90.0), 0.0, 0)
        val pointB = PolarPoint(Math.toRadians(225.0), 2 * sqrt(2.0), 0)
        forall(
            row(PolarPoint(Math.toRadians(90.0), 2.0, 0), 2.0, 4.4721),
            row(PolarPoint(Math.toRadians(45.0), sqrt(2.0), 0), sqrt(2.0), 3 * sqrt(2.0)),
            row(PolarPoint(Math.toRadians(135.0), sqrt(2.0), 0), sqrt(2.0), 3.1623)
        ) { otherPoint, distanceToA, distanceToB ->
            distance(pointA, otherPoint) shouldBe (distanceToA plusOrMinus 1e-4)
            distance(pointB, otherPoint) shouldBe (distanceToB plusOrMinus 1e-4)
        }
    }

    "distanceOriginLineToPoint" {
        forall(
            row(Math.toRadians(90.0), PolarPoint(Math.toRadians(45.0), sqrt(2.0), 0), 1.0),
            row(Math.toRadians(180.0), PolarPoint(Math.toRadians(45.0), sqrt(2.0), 0), sqrt(2.0)),
            row(Math.toRadians(270.0), PolarPoint(Math.toRadians(225.0), sqrt(2.0), 0), 1.0)
        ) { angle, point, distance ->
            distanceOriginLineToPoint(angle, point) shouldBe (distance plusOrMinus PRECISION)
        }
    }

    "distance PointToLine" {
        forall(
            row(
                Line(1, 3),
                Point(0, 0),
                3.0 / sqrt(2.0)
            ), row(
                Line(1, 3),
                Point(-1, -2),
                2.0 * sqrt(2.0)
            ), row(
                Line(-5, -2),
                Point(0.5, -4.5),
                0.0
            ), row(
                Line(-1, 0),
                Point(1, 1),
                sqrt(2.0)
            ), row(
                Line(0, 2),
                Point(1, 1),
                1.0
            )
        ) { line, point, distance ->
            distance(point, line) shouldBe (distance plusOrMinus PRECISION)
        }
    }

    "distance PointToSegment"{
        val point = Point(1, 1)
        forall(
            row(
                "point closest to line",
                LineSegment(Point(0, 2), Point(2, 2)),
                1.0
            ),
            row(
                "point on segment",
                LineSegment(Point(0, 1), Point(2, 1)),
                0.0
            ),
            row(
                "point closest to start- or endpoint",
                LineSegment(Point(2, 1), Point(3, 2)),
                1.0
            )
        ) { case, lineSegment, distance ->
            distance(point, lineSegment) shouldBe (distance plusOrMinus PRECISION)
        }
    }

    "distance SegmentToSegment"{
        val segment = LineSegment(Point(-2, -2), Point(2, 2))
        distance(segment, segment) shouldBe (0.0)

        forall(
            row(
                "startPoint closest",
                LineSegment(Point(-1, 1), Point(-10, 10)),
                sqrt(2.0)
            ),
            row(
                "endPoint closest",
                LineSegment(Point(0, 2), Point(1, 2)),
                sqrt(2.0) / 2
            ),
            row(
                "segments cross",
                LineSegment(Point(0, 1), Point(1, 0)),
                0.0
            ),
            row(
                "segments parallel",
                LineSegment(Point(-1, 0), Point(1, 2)),
                sqrt(2.0) / 2
            )
        ) { case, otherSegment, distance ->
            distance(segment, otherSegment) shouldBe (distance plusOrMinus PRECISION)
            distance(otherSegment, segment) shouldBe (distance plusOrMinus PRECISION)
        }
    }
})
