package de.tsteffek.model.geometry

import io.kotlintest.matchers.doubles.plusOrMinus
import io.kotlintest.shouldBe
import io.kotlintest.specs.FreeSpec
import io.kotlintest.tables.forAll
import io.kotlintest.tables.headers
import io.kotlintest.tables.row
import io.kotlintest.tables.table
import de.tsteffek.math.PRECISION
import kotlin.math.pow
import kotlin.math.sqrt

internal class PolarPointTest : FreeSpec({

        "normalizedDirection" {
            val pointsAndLengths = table(
                headers("point", "length"),
                row(
                    PolarPoint(Math.toRadians(45.0), 1.0, 0),
                    1.0
                ),
                row(
                    PolarPoint(Math.toRadians(45.0), sqrt(2.0), 0),
                    sqrt(2.0)
                ),
                row(
                    PolarPoint(Math.toRadians(45.0), 2 * sqrt(2.0), 0),
                    2 * sqrt(2.0)
                )
            )

            forAll(
                pointsAndLengths
            ) { point, _ ->
                val vec = point.normalizedDirection()
                sqrt(vec.first.pow(2) + vec.second.pow(2)) shouldBe (1.0 plusOrMinus PRECISION)
            }
        }

        "rotateBy" {
            val pointsAndRotations = table(
                headers("point", "rotation", "point after rotation"),
                row(
                    PolarPoint(0.0, 1.0, 0),
                    Math.toRadians(45.0),
                    PolarPoint(Math.toRadians(45.0), 1.0, 0)
                ),
                row(
                    PolarPoint(Math.toRadians(45.0), sqrt(2.0), 0),
                    Math.toRadians(-45.0),
                    PolarPoint(0.0, sqrt(2.0), 0)
                ),
                row(
                    PolarPoint(Math.toRadians(45.0), 2 * sqrt(2.0), 0),
                    Math.toRadians(180.0),
                    PolarPoint(Math.toRadians(225.0), 2 * sqrt(2.0), 0)
                )
            )

            forAll(
                pointsAndRotations
            ) { point, rotation, targetPoint ->
                val rotatedPoint = point.rotateBy(rotation)
                rotatedPoint shouldBe targetPoint
            }
        }

        "toTSV" {
            val point = PolarPoint(2.0, 3.0, 1)
            val targetString = "3.0\t2.0\t1"
            point.toTSVString() shouldBe targetString
        }

    "computes x and y correctly" {
        val pointsAndLengths = table(
            headers("polar distance", "polar angle", "cartesian x", "cartesian y", "quality"),
            row(1.0, Math.toRadians(90.0), 0.0, 1.0, 0),
            row(sqrt(2.0), Math.toRadians(45.0), 1.0, 1.0, 0),
            row(2 * sqrt(2.0), Math.toRadians(45.0), 2.0, 2.0, 0),
            row(2 * sqrt(2.0), Math.toRadians(-135.0), -2.0, -2.0, 0)
        )

        forAll(pointsAndLengths) { distance, angle, x, y, quality ->
            val point = PolarPoint(angle, distance, quality)
            point.x shouldBe (x plusOrMinus PRECISION)
            point.y shouldBe (y plusOrMinus PRECISION)
            point.quality shouldBe quality
        }
    }
})
