package model

import io.kotlintest.data.suspend.forall
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.FreeSpec
import io.kotlintest.tables.row
import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.verify
import math.distanceLineToPoint
import model.geometry.Line
import model.geometry.Point

internal class LineTest : FreeSpec({

    "distanceTo" - {
        val l = Line(1, 2)
        val point = Point(3, 4)
        val line = Line(1.5, 1)
        val parallelLine = Line(1, 1)

        "distanceTo Point calls distanceLineToPoint" {
            mockkStatic("math.GeometryKt")
            every { distanceLineToPoint(l, point) } returns 13.0
            l.distanceTo(point) shouldBe 13.0
            verify { distanceLineToPoint(l, point) }
        }

        "distanceTo Line is 0.0 or not implemented if parallel" {
            l.distanceTo(line) shouldBe 0.0

            shouldThrow<NotImplementedError> {
                l.distanceTo(parallelLine)
            }
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
