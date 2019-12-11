package model

import io.kotlintest.data.suspend.forall
import io.kotlintest.matchers.doubles.plusOrMinus
import io.kotlintest.shouldBe
import io.kotlintest.specs.FreeSpec
import io.kotlintest.tables.row
import model.geometry.*
import kotlin.math.PI
import kotlin.math.sqrt

internal class LineTest : FreeSpec({
    "piHalf" {
        piHalf shouldBe PI / 2
    }

    "distanceOriginLineToPoint" {
        forall(
            row(Math.toRadians(90.0), Point(Math.toRadians(45.0), sqrt(2.0), 0), 1.0),
            row(Math.toRadians(180.0), Point(Math.toRadians(45.0), sqrt(2.0), 0), sqrt(2.0)),
            row(Math.toRadians(270.0), Point(Math.toRadians(225.0), sqrt(2.0), 0), 1.0)
        ) { angle, point, distance ->
            distanceOriginLineToPoint(angle, point) shouldBe (distance plusOrMinus 1e-8)
        }
    }

    "distanceLineToPoint" {
        forall(
            row(
//                Line(0, 0),
//                Point(PI * 0.25, sqrt(2.0), 0),
//                1.0
//            ), row(
//                Line(PI, 1),
//                Point(PI * 0.25, sqrt(2.0), 0),
//                0.0
//            ), row(
                Line(PI * 1.75, sqrt(2.0)),
                Point(PI * 1.25, sqrt(2.0), 0),
                sqrt(2.0)
            ), row(
                Line(PI * 0.25, sqrt(2.0)),
                Point(PI * 0.5, 1, 0),
                sqrt(2.0)/2
            )
        ) { line, point, distance ->
            distanceLineToPoint(line, point) shouldBe (distance plusOrMinus 1e-8)
        }
    }

    "companion object" - {
        "fromTwoPoints" {
            forall(
                row(
                    Point(piHalf, 1, 0),
                    Point(piHalf / 2, sqrt(2.0), 1),
                    Line(piHalf, 1)
                ), row(
                    Point(PI * 0.75, 50, 0),
                    Point(PI * 1.75, sqrt(2.0), 1),
                    Line(PI * 0.75, 0)
                ), row(
                    Point(PI * 1.25, sqrt(2.0) / 2, 0),
                    Point(PI * 1.75, sqrt(2.0) / 2, 1),
                    Line(PI * 1.5, 0.5)
                ), row(
                    Point(PI * 1.75, sqrt(2.0), 0),
                    Point(PI * 1.5, 2, 1),
                    Line(PI * 1.75, sqrt(2.0))
                )
            ) { pointA, pointB, targetLine ->
                Line.fromTwoPoints(pointA, pointB) shouldBe targetLine
            }
        }
    }
})
