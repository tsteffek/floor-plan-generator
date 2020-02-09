package de.tsteffek.algorithms

import io.kotlintest.matchers.collections.shouldContainExactly
import io.kotlintest.specs.FreeSpec
import io.kotlintest.shouldBe
import de.tsteffek.model.NeighborhoodGraph
import de.tsteffek.model.geometry.Line
import de.tsteffek.model.geometry.Point

class ModelFittingTest : FreeSpec({

    "fitLines" - {
        "will detect a line" {
            val points = listOf(
                Point(-2, 2), // will detect line
                Point(-1, 3),
                Point(-3, 1)
            )
            val targetLines = listOf(
                Line(1, 4)
            )
            val graph = NeighborhoodGraph.usingBruteForce(points)
            val lines = fitLines(graph)
            lines.first() shouldContainExactly points
            lines.flatMap { it.asIterable() }.size shouldBe points.size
            lines.map { Line.fromSeveralPoints(it) } shouldContainExactly targetLines
        }

        "will detect two lines" {
            val targetPointSets = listOf(
                setOf(
                    Point(-2, 2),
                    Point(-1, 3),
                    Point(-3, 1)
                ),
                setOf(
                    Point(1, 2.5),
                    Point(2, 1)
                )
            )
            val points = targetPointSets.flatten()
            val targetLines = listOf(
                Line(1, 4),
                Line(-1.5, 4)
            )
            val graph = NeighborhoodGraph.usingBruteForce(points)
            val pointSets = fitLines(graph)
            pointSets shouldContainExactly targetPointSets
            pointSets.flatten().size shouldBe points.size
            pointSets.map { Line.fromSeveralPoints(it) } shouldContainExactly targetLines
        }

        "will detect a line without graph connection" {
            val targetPointSets = listOf(
                setOf(
                    Point(-2, 2),
                    Point(-1, 3),
                    Point(-3, 1)
                ),
                setOf(
                    Point(1, 2.5),
                    Point(2, 1)
                ),
                setOf(
                    Point(-50, -30),
                    Point(-51, -29),
                    Point(-52, -28)
                )
            )
            val points = targetPointSets.flatten()
            val targetLines = listOf(
                Line(1, 4),
                Line(-1.5, 4),
                Line(-1, -80)
            )
            val graph = NeighborhoodGraph.usingBruteForce(points)
            val pointSets = fitLines(graph)
            pointSets shouldContainExactly targetPointSets
            pointSets.flatten().size shouldBe points.size
            pointSets.map { Line.fromSeveralPoints(it) } shouldContainExactly targetLines
        }

        "will detect out of order points" {
            val sourcePointSets = listOf(
                setOf(
                    Point(-2, 2),
                    Point(-1, 3)
                ),
                setOf(
                    Point(1, 2.5),
                    Point(2, 1)
                ),
                setOf(
                    Point(-3, 1)
                ),
                setOf(
                    Point(-50, -30),
                    Point(-51, -29),
                    Point(-52, -28)
                )
            )
            val points = sourcePointSets.flatten()
            val targetPointSets = listOf(
                setOf(
                    Point(-2, 2),
                    Point(-1, 3),
                    Point(-3, 1)
                ),
                setOf(
                    Point(1, 2.5),
                    Point(2, 1)
                ),
                setOf(
                    Point(-50, -30),
                    Point(-51, -29),
                    Point(-52, -28)
                )
            )
            val targetLines = listOf(
                Line(1, 4),
                Line(-1.5, 4),
                Line(-1, -80)
            )
            val graph = NeighborhoodGraph.usingBruteForce(points)
            val pointSets = fitLines(graph)
            pointSets shouldContainExactly targetPointSets
            pointSets.flatten().size shouldBe points.size
            pointSets.map { Line.fromSeveralPoints(it) } shouldContainExactly targetLines
        }

        "works with trailing single point" {
            val targetPointSets = listOf(
                setOf(
                    Point(-2, 2),
                    Point(-1, 3),
                    Point(-3, 1)
                ),
                setOf(
                    Point(1, 2.5)
                )
            )
            val points = targetPointSets.flatten()
            val targetLines = listOf(
                Line(1, 4)
            )
            val graph = NeighborhoodGraph.usingBruteForce(points)
            val pointSets = fitLines(graph)
            pointSets shouldContainExactly targetPointSets
            pointSets.flatten().size shouldBe points.size
            pointSets
                .filter { it.size > 1 }
                .map { Line.fromSeveralPoints(it) } shouldContainExactly targetLines
        }
    }
})
