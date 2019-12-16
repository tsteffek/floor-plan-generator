package model

import io.kotlintest.assertSoftly
import io.kotlintest.data.forall
import io.kotlintest.matchers.asClue
import io.kotlintest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.FreeSpec
import io.kotlintest.tables.row
import model.geometry.Point
import model.geometry.PolarPoint
import mu.KotlinLogging

private val logger by lazy { KotlinLogging.logger {} }

class NeighborhoodGraphTest : FreeSpec({

    "getSize" {
        forall(
            row(
                listOf(Point(1, 0), Point(2, 0)), 2
            ), row(
                listOf(Point(1, 0), Point(2, 0), Point(3, 0)), 3
            ),
            row(
                listOf(Point(1, 0), Point(2, 0), Point(3, 0), Point(4, 0)), 4
            )
        ) { points, targetSize ->
            NeighborhoodGraph.usingBruteForce(points).getSize() shouldBe targetSize
        }
    }

    "companion object" - {
        val a = PolarPoint(0.0, 1.0, 0)
        val b = PolarPoint(Math.toRadians(-90.0), 1.0, 4)
        val c = PolarPoint(Math.toRadians(90.0), 1.0, 2)
        val d = PolarPoint(Math.toRadians(170.0), 0.9, 3)
        val outlier = PolarPoint(Math.toRadians(45.0), 50.0, 1)
        val unknownPoint = PolarPoint(Math.toRadians(90.0), 10.0, 500)

        val points = listOf(a, outlier, b, c, d)
        val inlier = listOf(a, b, c, d)
        val targetGraph = mapOf(
            a to listOf(b, c),
            b to listOf(a, d),
            c to listOf(d, a),
            d to listOf(c, b)
        )

        listOf(
            row("brute force", NeighborhoodGraph.usingBruteForce(points)),
            row("from polar points", NeighborhoodGraph.fromPolarPoints(points))
        ).map { (method, graph) ->
            "$method should filter outliers" {
                assertSoftly {
                    graph.getObjects() shouldContainExactlyInAnyOrder inlier
                }
            }
            "$method should throw error on unknown points" {
                assertSoftly {
                    shouldThrow<NoSuchElementException> { graph.getNeighbors(unknownPoint) }
                }
            }
            "$method should compute correct neighbors" {
                assertSoftly {
                    targetGraph.forEach { (point, targetNeighbors) ->
                        point.asClue {
                            graph.getNeighbors(point) shouldContainExactlyInAnyOrder targetNeighbors
                        }
                    }
                }
            }
        }
    }
})
