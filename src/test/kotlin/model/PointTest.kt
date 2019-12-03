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

    "distanceBetween" {
        val pointA = Point(0.0,0.0,0)
        val pointB = Point(-2.0,-2.0, 0)
        forall(
            row(Point(1.0,1.0,0), sqrt(2.0), 3* sqrt(2.0)),
            row(Point(-1.0,1.0,0), sqrt(2.0), 3.1623)
        ) { otherPoint, distanceToA, distanceToB ->
            pointA.distanceTo(otherPoint) shouldBe (distanceToA plusOrMinus 1e-5)
            pointB.distanceTo(otherPoint) shouldBe (distanceToB plusOrMinus 1e-5)
        }
    }

    "Point" - {
        val pointsAndLengths = table(
            headers("point", "length"),
            row(
                Point(0.0, 1.0, 0),
                1.0
            ),
            row(
                Point(1.0, 1.0, 0),
                sqrt(2.0)
            ),
            row(
                Point(2.0, 2.0, 0),
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
                -45.0,
                Point(0.7071067811865475, 0.7071067811865475, 0)
            ),
            row(
                Point(1.0, 1.0, 0),
                45.0,
                Point(0.0, sqrt(2.0), 0)
            ),
            row(
                Point(2.0, 2.0, 0),
                180.0,
                Point(-2.0, -2.0, 0)
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
            val point = Point(3.0, 2.0, 1)
            val targetString = "3.0\t2.0\t1"
            point.toTSVString() shouldBe targetString
        }

        "equals" {
            val x = 0.5
            val y = 2.3
            val quality = 50
            val point = Point(x, y, quality)
            forall(
                row(point),
                row(Point(x + 1e-8, y + 1e-8, quality)),
                row(Point(x, y, quality))
            ) { targetPoint ->
                point shouldBe targetPoint
            }

            forall(
                row(Point(x, y, quality + 1)),
                row(Point(x + 2e-8, y, quality)),
                row(Point(x, y + 2e-8, quality)),
                row(2.0)
                ) {targetPoint ->
                point shouldNotBe targetPoint
            }
        }
    }

    "companion object" - {
        val pointsAndLengths = table(
            headers("polar distance", "polar angle", "quality", "point in cartesian"),
            row(
                1.0, 90.0, 0,
                Point(0.0, 1.0, 0)
            ),
            row(
                sqrt(2.0), 45.0, 0,
                Point(1.0, 1.0, 0)

            ),
            row(
                2 * sqrt(2.0), 45.0, 0,
                Point(2.0, 2.0, 0)
            ),
            row(
                2 * sqrt(2.0), -135.0, 0,
                Point(-2.0, -2.0, 0)
            )
        )

        "fromPolarCoordinates" {
            forAll(pointsAndLengths) { distance, angle, quality, targetPoint ->
                val point = Point(distance, angle, quality)
                point shouldBe targetPoint
            }
        }
    }
})
