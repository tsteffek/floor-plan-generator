package model

import io.kotlintest.data.suspend.forall
import io.kotlintest.matchers.doubles.plusOrMinus
import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import io.kotlintest.specs.FreeSpec
import io.kotlintest.tables.forAll
import io.kotlintest.tables.headers
import io.kotlintest.tables.row
import io.kotlintest.tables.table
import model.geometry.Point
import kotlin.math.pow
import kotlin.math.sqrt

internal class PointTest : FreeSpec({

    "distancePointToPoint" {
        val pointA = Point(Math.toRadians(90.0), 0.0, 0)
        val pointB = Point(Math.toRadians(225.0), 2 * sqrt(2.0), 0)
        forall(
            row(Point(Math.toRadians(90.0), 2.0, 0), 2.0, 4.4721),
            row(Point(Math.toRadians(45.0), sqrt(2.0), 0), sqrt(2.0), 3 * sqrt(2.0)),
            row(Point(Math.toRadians(135.0), sqrt(2.0), 0), sqrt(2.0), 3.1623)
        ) { otherPoint, distanceToA, distanceToB ->
            pointA.distanceTo(otherPoint) shouldBe (distanceToA plusOrMinus 1e-4)
            pointB.distanceTo(otherPoint) shouldBe (distanceToB plusOrMinus 1e-4)
        }
    }

    "Point" - {
        val pointsAndLengths = table(
            headers("point", "length"),
            row(
                Point(Math.toRadians(45.0), 1.0, 0),
                1.0
            ),
            row(
                Point(Math.toRadians(45.0), sqrt(2.0), 0),
                sqrt(2.0)
            ),
            row(
                Point(Math.toRadians(45.0), 2 * sqrt(2.0), 0),
                2 * sqrt(2.0)
            )
        )

        "normalizedDirection" {
            forAll(
                pointsAndLengths
            ) { point, _ ->
                val vec = point.normalizedDirection()
                sqrt(vec.first.pow(2) + vec.second.pow(2)) shouldBe (1.0 plusOrMinus 1e-5)
            }
        }

        val pointsAndRotations = table(
            headers("point", "rotation", "point after rotation"),
            row(
                Point(0.0, 1.0, 0),
                Math.toRadians(45.0),
                Point(Math.toRadians(45.0), 1.0, 0)
            ),
            row(
                Point(Math.toRadians(45.0), sqrt(2.0), 0),
                Math.toRadians(-45.0),
                Point(0.0, sqrt(2.0), 0)
            ),
            row(
                Point(Math.toRadians(45.0), 2 * sqrt(2.0), 0),
                Math.toRadians(180.0),
                Point(Math.toRadians(225.0), 2 * sqrt(2.0), 0)
            )
        )

        "rotateBy" {
            forAll(
                pointsAndRotations
            ) { point, rotation, targetPoint ->
                val rotatedPoint = point.rotateBy(rotation)
                rotatedPoint shouldBe targetPoint
            }
        }

        "toTSV" {
            val point = Point(2.0, 3.0, 1)
            val targetString = "3.0\t2.0\t1"
            point.toTSVString() shouldBe targetString
        }

        "equals" {
            val distance = 0.5
            val angle = 2.3
            val quality = 50
            val point = Point(angle, distance, quality)
            forall(
                row(point),
                row(Point(angle, distance + 1e-8, quality)),
                row(Point(angle, distance, quality))
            ) { targetPoint ->
                point shouldBe targetPoint
            }

            forall(
                row(Point(angle, distance, quality + 1)),
                row(Point(angle, distance + 0.01, quality)),
                row(Point(angle + 0.01, distance, quality)),
                row(2.0)
            ) { targetPoint ->
                point shouldNotBe targetPoint
            }
        }
    }

    val pointsAndLengths = table(
        headers("polar distance", "polar angle", "cartesian x", "cartesian y", "quality"),
        row(1.0, Math.toRadians(90.0), 0.0, 1.0, 0),
        row(sqrt(2.0), Math.toRadians(45.0), 1.0, 1.0, 0),
        row(2 * sqrt(2.0), Math.toRadians(45.0), 2.0, 2.0, 0),
        row(2 * sqrt(2.0), Math.toRadians(-135.0), -2.0, -2.0, 0)
    )

    "computes x and y correctly" {
        forAll(pointsAndLengths) { distance, angle, x, y, quality ->
            val point = Point(angle, distance, quality)
            point.x shouldBe (x plusOrMinus 1e-5)
            point.y shouldBe (y plusOrMinus 1e-5)
            point.quality shouldBe quality
        }
    }
})
