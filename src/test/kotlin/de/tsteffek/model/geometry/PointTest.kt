package de.tsteffek.model.geometry

import io.kotlintest.data.suspend.forall
import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import io.kotlintest.specs.FreeSpec
import io.kotlintest.tables.row
import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.verifyAll
import de.tsteffek.math.PRECISION
import de.tsteffek.math.distance

internal class PointTest : FreeSpec({

    "constructors are all equal" {
        val point = Point(1.0, 2.0)
        forall(
            row(Point(1, 2.0)),
            row(Point(1.0, 2)),
            row(Point(1, 2))
        ) {
            point shouldBe it
        }
    }

    "liesOn Line" {
        val p = Point(1, 1)
        forall(
            row(LineSegment(Point(1, 1), Point(2, 2)), true),
            row(LineSegment(Point(0, 0), Point(2, 2)), true),
            row(LineSegment(Point(0, 0), Point(-2, 2)), false)
        ) { lineSegment, targetBool ->
            p.liesOn(lineSegment as Line) shouldBe targetBool
        }
    }

    "liesOn LineSegment" {
        val p = Point(1, 1)
        forall(
            row(LineSegment(Point(1, 1), Point(2, 2)), true),
            row(LineSegment(Point(0, 0), Point(2, 2)), true),
            row(LineSegment(Point(0, 0), Point(-2, 2)), false)
        ) { lineSegment, targetBool ->
            p.liesOn(lineSegment) shouldBe targetBool
        }
    }

    "distanceTo" {
        val p = Point(1, 2)
        val otherP = Point(3, 4)
        val line = Line(1, 1)
        mockkStatic("de.tsteffek.math.GeometryKt")
        every { distance(otherP, p) } returns 13.0
        every { distance(p, line) } returns 23.0

        p.distanceTo(otherP) shouldBe 13.0
        p.distanceTo(line) shouldBe 23.0

        verifyAll {
            distance(p, line)
            distance(otherP, p)
        }
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
            row(Point(angle, distance + PRECISION)),
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
