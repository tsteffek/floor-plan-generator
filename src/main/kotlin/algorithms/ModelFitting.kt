package algorithms

import math.distanceLineToPoint
import model.NeighborhoodGraph
import model.geometry.Line
import model.geometry.Point
import java.util.LinkedList

/** Threshold for line fitting, defaults to 1mm deviation from the line */
const val THRESHOLD = 1.0 / 1000.0

/**
 * Tries to fit lines onto the [T]s in a [NeighborhoodGraph] [graph] using a very basic, one-iteration method (used as initialisation step for more complex algorithms).
 * @return a list containing sets of points which have been fitted together (might contain sets with just one [T])
 */
fun <T : Point> fitLines(graph: NeighborhoodGraph<T>): List<Set<T>> =
    fitLines(graph, graph.getObjects())

// this warns, that tailrec parameter defaults will be initialized in reverse order - which doesn't matter here.
@Suppress("TAILREC_WITH_DEFAULTS")
private tailrec fun <T : Point> fitLines(
    graph: NeighborhoodGraph<T>,
    points: Set<T> = graph.getObjects(),
    takenPoints: MutableSet<T> = HashSet(),
    lines: MutableList<Set<T>> = mutableListOf()
): List<Set<T>> {
    if (points.isEmpty()) return lines

    val startPoint = points.first()

    val initialNeighbors = LinkedList(getNotTakenNeighbors(startPoint, takenPoints, graph))
    lines.add(findNeighborsOnLine(setOf(startPoint), initialNeighbors, takenPoints, graph))

    return fitLines(graph, graph.getObjects() - takenPoints, takenPoints, lines)
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
