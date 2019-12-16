package algorithms

import math.distanceLineToPoint
import model.NeighborhoodGraph
import model.geometry.Line
import model.geometry.Point
import java.util.LinkedList

fun <T : Point> fitLinesRec(graph: NeighborhoodGraph<T>): List<Set<T>> =
    fitLinesRec(graph.getObjects(), graph = graph)

@Suppress("TAILREC_WITH_DEFAULTS") // this warns, that tailrec parameter defaults will be initialized in reverse order - which doesn't matter here.
tailrec fun <T : Point> fitLinesRec(
    points: Set<T>,
    takenPoints: MutableSet<T> = HashSet(),
    graph: NeighborhoodGraph<T>,
    lines: MutableList<Set<T>> = mutableListOf()
): List<Set<T>> {
    if (points.isEmpty()) return lines

    val startPoint = points.first()

    val initialNeighbors = LinkedList(getNotTakenNeighbors(startPoint, takenPoints, graph))
    lines.add(findNeighborsOnLine(setOf(startPoint), initialNeighbors, takenPoints, graph))

    return fitLinesRec(graph.getObjects() - takenPoints, takenPoints, graph, lines)
}

private tailrec fun <T : Point> findNeighborsOnLine(
    linePoints: Set<T>,
    nearestPoints: LinkedList<T>,
    takenPoints: MutableSet<T>,
    graph: NeighborhoodGraph<T>
): Set<T> {
    if (nearestPoints.isEmpty() || takenPoints.size == graph.getSize()) {
        return linePoints
    }
    val nearestPoint = nearestPoints.pop()
    val newLinePoints = linePoints + nearestPoint

    val line = Line.fromSeveralPoints(newLinePoints)
    val maxDistance = newLinePoints.map { distanceLineToPoint(line, it) }.max()!!

    return if (maxDistance > 0.0001) {
        findNeighborsOnLine(linePoints, nearestPoints, takenPoints, graph)
    } else {
        takenPoints.add(nearestPoint)
        val notTakenNeighbors = getNotTakenNeighbors(nearestPoint, takenPoints, graph)
        nearestPoints.addAll(notTakenNeighbors)
        findNeighborsOnLine(newLinePoints, nearestPoints, takenPoints, graph)
    }
}

private fun <T : Point> getNotTakenNeighbors(point: T, takenPoints: Set<T>, graph: NeighborhoodGraph<T>): List<T> =
    graph.getNeighbors(point).filterNot { takenPoints.contains(it) }
