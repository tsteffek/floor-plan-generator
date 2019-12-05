package model

import io.kotlintest.assertSoftly
import io.kotlintest.matchers.asClue
import io.kotlintest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotlintest.shouldThrow
import io.kotlintest.specs.FreeSpec
import io.kotlintest.tables.row
import model.geometry.Point
import mu.KotlinLogging

private val logger by lazy { KotlinLogging.logger {} }

class NeighborhoodGraphTest : FreeSpec({

    "companion object" - {
        val a = Point(1.0, 0.0, 0)
        val b = Point(1.0, -90.0, 4)
        val c = Point(1.0, 90.0, 2)
        val d = Point(0.9, 170.0, 3)
        val outlier = Point(50.0, 45.0, 1)
        val unknownPoint = Point(10.0, 90.0, 500)

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
