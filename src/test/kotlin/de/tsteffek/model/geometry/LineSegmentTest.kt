package de.tsteffek.model.geometry

import io.kotlintest.data.forall
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.FreeSpec
import io.kotlintest.tables.row
import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.verify
import de.tsteffek.math.distance
import kotlin.math.PI
import kotlin.math.sqrt

internal class LineSegmentTest : FreeSpec({

    "inBoundingBox" {
        val point = Point(1, 1)
        forall(
            row("point in boundingBox", LineSegment(Point(0.5, 0.5), Point(2, 3)), true),
            row("point outside of boundingBox", LineSegment(Point(2, 2), Point(1.1, 1.1)), false)
        ) { case, lineSegment, targetBool ->
            lineSegment.inBoundingBox(point) shouldBe targetBool
        }
    }

    "distanceTo" - {
        val l = LineSegment(Point(1, 1), Point(2, 2))
        val point = Point(1, 2)
        val line = Line(1.5, 1)
        val parallelLine = Line(1, 1)

        "distanceTo Point calls distanceLineToPoint" {
            mockkStatic("de.tsteffek.math.GeometryKt")
            every { distance(point, l) } returns 13.0
            l.distanceTo(point) shouldBe 13.0
            verify { distance(point, l) }
        }

        "distanceTo LineSegment calls distance SegmentToSegment" {
            mockkStatic("de.tsteffek.math.GeometryKt")
            every { distance(l, l) } returns 13.0
            l.distanceTo(l) shouldBe 13.0
            verify { distance(l, l) }
        }

        "distanceTo Line is 0.0 or not implemented if parallel" {
            l.distanceTo(line) shouldBe 0.0

            shouldThrow<NotImplementedError> {
                l.distanceTo(parallelLine)
            }
        }
    }

    "companion object" - {
        "fromSeveralPoints from 2 points" {
            forall(
                row(
                    listOf(
                        PolarPoint(0, 0, 0),
                        PolarPoint(PI * 0.25, sqrt(2.0), 1)
                    ),
                    LineSegment(
                        Point(0, 0),
                        Point(1, 1)
                    )
                ), row(
                    listOf(
                        PolarPoint(0, 0, 2),
                        PolarPoint(PI * 0.75, sqrt(2.0), 3)
                    ),
                    LineSegment(
                        Point(0, 0),
                        Point(-1, 1)
                    )
                ), row(
                    listOf(
                        PolarPoint(PI * 1.25, sqrt(2.0), 4),
                        PolarPoint(PI * 1.5, 1, 5)
                    ),
                    LineSegment(
                        Point(-1, -1),
                        Point(0, -1)
                    )
                )
            ) { sortedPoints, targetLine ->
                val segment = LineSegment.fromSeveralPoints(sortedPoints)
                segment shouldBe targetLine
                segment.startPoint shouldBe targetLine.startPoint
                segment.endPoint shouldBe targetLine.endPoint
            }
        }

        "fromSeveralPoints from 3 points" {
            forall(
                row(
                    listOf(
                        PolarPoint(0, 0, 0),
                        PolarPoint(PI * 0.25, 0.5, 1),
                        PolarPoint(PI * 0.25, sqrt(2.0), 2)
                    ),
                    LineSegment(
                        Point(0, 0),
                        Point(1, 1)
                    )
                ), row(
                    listOf(
                        PolarPoint(0, 0, 2),
                        PolarPoint(PI * 0.75, 0.5, 2),
                        PolarPoint(PI * 0.75, sqrt(2.0), 3)
                    ),
                    LineSegment(
                        Point(0, 0),
                        Point(-1, 1)
                    )
                ), row(
                    listOf(
                        PolarPoint(PI * 1.25, sqrt(2.0), 4),
                        PolarPoint(PI * 1.25, sqrt(2.0), 4),
                        PolarPoint(PI * 1.5, 1, 5)
                    ),
                    LineSegment(
                        Point(-1, -1),
                        Point(0, -1)
                    )
                )
            ) { sortedPoints, targetLine ->
                val segment = LineSegment.fromSeveralPoints(sortedPoints)
                segment shouldBe targetLine
                segment.startPoint shouldBe targetLine.startPoint
                segment.endPoint shouldBe targetLine.endPoint
            }
        }

    }
})
