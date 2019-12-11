package model

import io.kotlintest.data.suspend.forall
import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import io.kotlintest.specs.FreeSpec
import io.kotlintest.tables.row
import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.verify
import math.distanceLineToPoint
import math.distancePointToPoint
import model.geometry.Line
import model.geometry.Point

internal class PointTest : FreeSpec({


    "distanceTo" {
        val p = Point(1, 2)
        val otherP = Point(3, 4)
        val line = Line(1, 1)

        mockkStatic("math.GeometryKt")
        every { distancePointToPoint(otherP, p) } returns 13.0
        every { distanceLineToPoint(line, p) } returns 23.0

        p.distanceTo(otherP) shouldBe 13.0
        p.distanceTo(line) shouldBe 23.0

        verify { distanceLineToPoint(line, p) }
        verify { distancePointToPoint(otherP, p) }
    }

    "toTSV" {
        val point = Point(2.0, 3.0)
        val targetString = "2.0\t3.0"
        point.toTSVString() shouldBe targetString
    }

    "equals" {
        val distance = 0.5
        val angle = 2.3
        val point = Point(angle, distance)

        forall(
            row(point),
            row(Point(angle, distance + 1e-8)),
            row(Point(angle, distance))
        ) { targetPoint ->
            point shouldBe targetPoint
        }

        forall(
            row(Point(angle, distance + 0.01)),
            row(Point(angle + 0.01, distance)),
            row(2.0)
        ) { targetPoint ->
            point shouldNotBe targetPoint
        }
    }
})
