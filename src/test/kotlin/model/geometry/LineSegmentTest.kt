package model.geometry

import io.kotlintest.data.forall
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.FreeSpec
import io.kotlintest.tables.row
import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.verify
import math.distance

internal class LineSegmentTest : FreeSpec({

    "inBoundingBox" {
        val point = Point(1,1)
        forall(
            row("point in boundingBox", LineSegment(Point(0.5,0.5), Point(2,3)), true),
            row("point outside of boundingBox", LineSegment(Point(2,2), Point(1.1,1.1)), false)
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
            mockkStatic("math.GeometryKt")
            every { distance(point, l) } returns 13.0
            l.distanceTo(point) shouldBe 13.0
            verify { distance(point, l) }
        }

        "distanceTo LineSegment calls distance SegmentToSegment" {
            mockkStatic("math.GeometryKt")
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
})
