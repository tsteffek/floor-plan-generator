package model;

import io.kotlintest.*
import io.kotlintest.matchers.collections.shouldContain
import io.kotlintest.matchers.doubles.plusOrMinus
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.*
import mu.KotlinLogging
import kotlin.math.pow
import kotlin.math.sqrt

private val logger by lazy { KotlinLogging.logger {} }

class PointTest : StringSpec() {
    private lateinit var pointsAndLengths: Table2<Point, Double>

    override fun beforeTest(testCase: TestCase) {
        super.beforeTest(testCase)
        pointsAndLengths = table(
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
                Point(Pair(0.0, 1.0), 1.0, 0),
                1.0
            ),
            row(
                Point(Pair(0.0, 1.0), 2.0, 0),
                2.0
            ),
            row(
                Point(Pair(1.0, 1.0), 2.0, 0),
                2 * sqrt(2.0)
            )
        )
    }

    init {
        "point length" {
            forAll(
                pointsAndLengths
            ) { point, length ->
                point.getLength() shouldBe length
            }
        }
        "normalizedVec" {
            forAll(
                pointsAndLengths
            ) { point, _ ->
                val vec = point.normalizedVec()
                sqrt(vec.first.pow(2) + vec.second.pow(2)) shouldBe (1.0 plusOrMinus 1e-5)
            }
        }
    }
}
