package model.geometry

import de.tsteffek.model.geometry.Line
import de.tsteffek.model.geometry.Point
import io.kotlintest.data.suspend.forall
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.FreeSpec
import io.kotlintest.tables.row
import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.verify
import de.tsteffek.math.distanceLineToPoint

internal class LineTest : FreeSpec({

    "constructors are all equal" {
        val line = Line(1.0, 2.0)
        forall(
            row(Line(1, 2.0)),
            row(Line(1.0, 2)),
            row(Line(1, 2))
        ) {
            line shouldBe it
        }
    }

    "distanceTo" - {
        val l = Line(1, 2)
        val point = Point(3, 4)
        val line = Line(1.5, 1)
        val parallelLine = Line(1, 1)

        "distanceTo Point calls distanceLineToPoint" {
            mockkStatic("de.tsteffek.math.GeometryKt")
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

    "toTSV" {
        val line = Line(2.0, 3.0)
        val targetString = "2.0\t3.0"
        line.toTSVString() shouldBe targetString
    }

    "companion object" - {
        "fromTwoPoints" {
            forall(
                row(
                    Point(0, 0),
                    Point(1, 1),
                    Line(1, 0)
                ), row(
                    Point(1, -1),
                    Point(0, 0),
                    Line(-1, 0)
                ), row(
                    Point(-2, -3),
                    Point(-1, -3),
                    Line(0, -3)
                )
            ) { pointA, pointB, targetLine ->
                Line.fromTwoPoints(pointA, pointB) shouldBe targetLine
            }
        }

        "fromSeveralPoints from 2 points" {
            forall(
                row(
                    listOf(
                        Point(0, 0),
                        Point(1, 1)
                    ),
                    Line(1, 0)
                ), row(
                    listOf(
                        Point(1, -1),
                        Point(0, 0)
                    ),
                    Line(-1, 0)
                ), row(
                    listOf(
                        Point(-2, -3),
                        Point(-1, -3)
                    ),
                    Line(0, -3)
                )
            ) { points, targetLine ->
                Line.fromSeveralPoints(points) shouldBe targetLine
            }
        }

        "fromSeveralPoints from 3 points" {
            forall(
                row(
                    listOf(
                        Point(0, 0),
                        Point(1, 1),
                        Point(1, 1)
                    ),
                    Line(1, 0)
                ), row(
                    listOf(
                        Point(1, -1),
                        Point(0, 0),
                        Point(2, -2)
                    ),
                    Line(-1, 0)
                ), row(
                    listOf(
                        Point(-2, -3),
                        Point(-1, -3),
                        Point(0, -3)
                    ),
                    Line(0, -3)
                )
            ) { points, targetLine ->
                Line.fromSeveralPoints(points) shouldBe targetLine
            }
        }

    }
})
