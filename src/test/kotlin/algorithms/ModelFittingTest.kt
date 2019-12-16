package algorithms

import io.kotlintest.matchers.collections.shouldContainExactly
import io.kotlintest.specs.FreeSpec
import io.kotlintest.shouldBe
import model.NeighborhoodGraph
import model.geometry.Line
import model.geometry.Point

class ModelFittingTest : FreeSpec({

    "initRecursion" - {
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
            lines.flatMap { it.asIterable() }.size shouldBe points.size
            lines.map { Line.fromSeveralPoints(it) } shouldContainExactly targetLines
        }

        "will detect two lines" {
            val points = listOf(
                Point(-2, 2),
                Point(-1, 3),
                Point(-3, 1),

                Point(1, 2.5),
                Point(2, 1)
            )
            val targetLines = listOf(
                Line(1, 4),
                Line(-1.5, 4)
                )
            val graph = NeighborhoodGraph.usingBruteForce(points)
            val lines = fitLines(graph)
            lines.flatMap { it.asIterable() }.size shouldBe points.size
            lines.map { Line.fromSeveralPoints(it) } shouldContainExactly targetLines
        }

        "will detect a line without graph connection" {
            val points = listOf(
                Point(-2, 2),
                Point(-1, 3),
                Point(-3, 1),

                Point(1, 2.5),
                Point(2, 1),

                Point(-50, -30),
                Point(-51, -29),
                Point(-52, -28)
            )
            val targetLines = listOf(
                Line(1, 4),
                Line(-1.5, 4),
                Line(-1, -80)
            )
            val graph = NeighborhoodGraph.usingBruteForce(points)
            val lines = fitLines(graph)
            lines.flatMap { it.asIterable() }.size shouldBe points.size
            lines.map { Line.fromSeveralPoints(it) } shouldContainExactly targetLines
        }

        "will detect out of order points" {
            val points = listOf(
                Point(-2, 2),
                Point(-1, 3),

                Point(1, 2.5),
                Point(2, 1),

                Point(-3, 1),

                Point(-50, -30),
                Point(-51, -29),
                Point(-52, -28)
            )
            val targetLines = listOf(
                Line(1, 4),
                Line(-1.5, 4),
                Line(-1, -80)
            )
            val graph = NeighborhoodGraph.usingBruteForce(points)
            val lines = fitLines(graph)
            lines.flatMap { it.asIterable() }.size shouldBe points.size
            lines.map { Line.fromSeveralPoints(it) } shouldContainExactly targetLines
        }
    }
})
