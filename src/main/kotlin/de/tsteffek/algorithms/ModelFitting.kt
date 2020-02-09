package de.tsteffek.algorithms

import de.tsteffek.math.distance
import de.tsteffek.model.NeighborhoodGraph
import de.tsteffek.model.geometry.Line
import de.tsteffek.model.geometry.Point
import java.util.LinkedList

/** Threshold for line fitting, defaults to 1mm deviation from the line. */
const val THRESHOLD = 1.0 / 100.0

fun <T : Point> fitLines(graph: NeighborhoodGraph<T>): List<Set<T>> =
    fitLines(graph.getObjects(), graph = graph)

// this warns, that tailrec parameter defaults will be initialized in reverse order - which doesn't matter here.
@Suppress("TAILREC_WITH_DEFAULTS")
tailrec fun <T : Point> fitLines(
    points: Set<T>,
    takenPoints: MutableSet<T> = HashSet(),
    graph: NeighborhoodGraph<T>,
    lines: MutableList<Set<T>> = mutableListOf()
): List<Set<T>> {
    if (points.isEmpty()) return lines

    val startPoint = points.first()
    takenPoints.add(startPoint)

    val initialNeighbors = LinkedList(getNotTakenNeighbors(startPoint, takenPoints, graph))
    lines.add(findNeighborsOnLine(setOf(startPoint), initialNeighbors, takenPoints, graph))

    return fitLines(graph.getObjects() - takenPoints, takenPoints, graph, lines)
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
    val maxDistance = newLinePoints.map { distance(it, line) }.max()!!

    return if (maxDistance > THRESHOLD) {
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
