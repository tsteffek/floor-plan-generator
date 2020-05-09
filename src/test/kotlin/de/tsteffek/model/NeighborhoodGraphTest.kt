package de.tsteffek.model

import io.kotlintest.assertSoftly
import io.kotlintest.data.forall
import io.kotlintest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.FreeSpec
import io.kotlintest.tables.row
import de.tsteffek.model.geometry.Point
import de.tsteffek.model.geometry.PolarPoint
import io.kotlintest.matchers.asClue
import mu.KotlinLogging
import java.util.*
import kotlin.NoSuchElementException

private val logger by lazy { KotlinLogging.logger {} }

class NeighborhoodGraphTest : FreeSpec({

    "getSize" {
        forall(
            row(
                mapOf<Point, Set<Point>>(
                    Point(1, 0) to Collections.emptySet(),
                    Point(2, 0) to Collections.emptySet()
                ), 2
            ), row(
                mapOf<Point, Set<Point>>(
                    Point(1, 0) to Collections.emptySet(),
                    Point(2, 0) to Collections.emptySet(),
                    Point(3, 0) to Collections.emptySet()
                ), 3
            ),
            row(
                mapOf<Point, Set<Point>>(
                    Point(1, 0) to Collections.emptySet(),
                    Point(2, 0) to Collections.emptySet(),
                    Point(3, 0) to Collections.emptySet(),
                    Point(4, 0) to Collections.emptySet()
                ), 4
            )
        ) { pointsMap, targetSize ->
            NeighborhoodGraph(pointsMap).getSize() shouldBe targetSize
        }
    }

    "companion object" - {
        val a = PolarPoint(0.0, 1.0, 0)
        val b = PolarPoint(Math.toRadians(-90.0), 1.0, 4)
        val c = PolarPoint(Math.toRadians(90.0), 1.0, 2)
        val d = PolarPoint(Math.toRadians(170.0), 0.9, 3)

        val inlier = listOf(a, b, c, d)
        val targetGraph = mapOf(
            a to listOf(b, c),
            b to listOf(a, d),
            c to listOf(d, a),
            d to listOf(c, b)
        )

        val outlier = PolarPoint(Math.toRadians(45.0), 50.0, 1)
        val allPoints = listOf(a, outlier, b, c, d)

        val unknownPoint = PolarPoint(Math.toRadians(90.0), 10.0, 500)

        listOf(
            row("brute force", NeighborhoodGraph.fromBruteForce(allPoints)),
            row("from polar points", NeighborhoodGraph.fromPolarPoints(allPoints))
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

        "from polar points accepts multiple passes" {
            val a2 = PolarPoint(0.0, 1.0, 10)
            val b2 = PolarPoint(Math.toRadians(-90.0), 1.0, 14)
            val c2 = PolarPoint(Math.toRadians(90.0), 1.0, 12)
            val d2 = PolarPoint(Math.toRadians(170.0), 0.9, 13)

            val targetGraph2Pass = mapOf(
                a to listOf(a2, b),
                a2 to listOf(c2, a),
                b to listOf(b2, d),
                b2 to listOf(a2, b),
                c to listOf(c2, a),
                c2 to listOf(d2, c),
                d to listOf(d2, c),
                d2 to listOf(b2, d)
            )

            val allPoints2Pass = listOf(a, b, c, d, a2, b2, c2, d2)
            val graph = NeighborhoodGraph.fromPolarPoints(allPoints2Pass)

            assertSoftly {
                targetGraph2Pass.forEach { (point, targetNeighbors) ->
                    point.asClue {
                        graph.getNeighbors(point) shouldContainExactlyInAnyOrder targetNeighbors
                    }
                }
            }
        }
    }
})
