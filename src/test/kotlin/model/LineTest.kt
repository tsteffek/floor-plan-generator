package model

import io.kotlintest.data.suspend.forall
import io.kotlintest.matchers.doubles.plusOrMinus
import io.kotlintest.shouldBe
import io.kotlintest.specs.FreeSpec
import io.kotlintest.tables.row
import maths.distanceLineToPoint
import maths.distanceOriginLineToPoint
import model.geometry.*
import kotlin.math.PI
import kotlin.math.sqrt

internal class LineTest : FreeSpec({
    "piHalf" {
        piHalf shouldBe PI / 2
    }

    "distanceOriginLineToPoint" {
        forall(
            row(Math.toRadians(90.0), PolarPoint(Math.toRadians(45.0), sqrt(2.0), 0), 1.0),
            row(Math.toRadians(180.0), PolarPoint(Math.toRadians(45.0), sqrt(2.0), 0), sqrt(2.0)),
            row(Math.toRadians(270.0), PolarPoint(Math.toRadians(225.0), sqrt(2.0), 0), 1.0)
        ) { angle, point, distance ->
            distanceOriginLineToPoint(angle, point) shouldBe (distance plusOrMinus 1e-8)
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
            )
        ) { line, point, distance ->
            distanceLineToPoint(line, point) shouldBe (distance plusOrMinus 1e-8)
        }
    }

    "companion object" - {
        "fromTwoPoints" {
            forall(
                row(
                    Point(0, 0),
                    Point(1, 1),
                    Line(1, 0)
                ), row(
                    Point(1, 1),
                    Point(0, 0),
                    Line(1, 0)
                ), row(
                    Point(-2, -3),
                    Point(-1, -3),
                    Line(0, -3)
                )
            ) { pointA, pointB, targetLine ->
                Line.fromTwoPoints(pointA, pointB) shouldBe targetLine
            }
        }
    }
})
