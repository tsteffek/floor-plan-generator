package math

import io.kotlintest.data.suspend.forall
import io.kotlintest.matchers.doubles.plusOrMinus
import io.kotlintest.shouldBe
import io.kotlintest.specs.FreeSpec
import io.kotlintest.tables.row
import de.tsteffek.model.geometry.Line
import de.tsteffek.model.geometry.Point
import de.tsteffek.model.geometry.PolarPoint
import de.tsteffek.math.*
import kotlin.math.PI
import kotlin.math.sqrt

internal class GeometryTest : FreeSpec({

    "piHalf" {
        PIHALF shouldBe PI / 2
    }

    "lengthOf" {
        forall(
            row(1.0, 0.0, 1.0),
            row(0.0, 1.0, 1.0),
            row(1.0, 1.0, sqrt(2.0)),
            row(-1.0, -1.0, sqrt(2.0))
        ) { x, y, length ->
            lengthOf(
                x,
                y
            ) shouldBe (length plusOrMinus PRECISION)
        }
    }

    "distancePointToPoint" {
        val pointA = PolarPoint(Math.toRadians(90.0), 0.0, 0)
        val pointB = PolarPoint(Math.toRadians(225.0), 2 * sqrt(2.0), 0)
        forall(
            row(PolarPoint(Math.toRadians(90.0), 2.0, 0), 2.0, 4.4721),
            row(PolarPoint(Math.toRadians(45.0), sqrt(2.0), 0), sqrt(2.0), 3 * sqrt(2.0)),
            row(PolarPoint(Math.toRadians(135.0), sqrt(2.0), 0), sqrt(2.0), 3.1623)
        ) { otherPoint, distanceToA, distanceToB ->
            pointA.distanceTo(otherPoint) shouldBe (distanceToA plusOrMinus 1e-4)
            pointB.distanceTo(otherPoint) shouldBe (distanceToB plusOrMinus 1e-4)
        }
    }

    "distanceOriginLineToPoint" {
        forall(
            row(Math.toRadians(90.0),
                PolarPoint(Math.toRadians(45.0), sqrt(2.0), 0), 1.0),
            row(Math.toRadians(180.0),
                PolarPoint(Math.toRadians(45.0), sqrt(2.0), 0), sqrt(2.0)),
            row(Math.toRadians(270.0),
                PolarPoint(Math.toRadians(225.0), sqrt(2.0), 0), 1.0)
        ) { angle, point, distance ->
            distanceOriginLineToPoint(
                angle,
                point
            ) shouldBe (distance plusOrMinus PRECISION)
        }
    }

    "distanceLineToPoint" {
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
            distanceLineToPoint(
                line,
                point
            ) shouldBe (distance plusOrMinus PRECISION)
        }
    }
})
