package model

import model.geometry.GeometricObject
import model.geometry.Point
import model.geometry.distanceOriginLineToPoint
import model.geometry.distancePointToPoint

class NeighborhoodGraph<T : GeometricObject<T>>(
    private val map: Map<T, Set<T>>
) {
    fun getObjects(): Set<T> = map.keys

    fun getNeighbors(t: T): Set<T> = map.getValue(t)

    companion object {
        fun <T : GeometricObject<T>> usingBruteForce(objects: List<T>): NeighborhoodGraph<T> {
            val closestNeighborsPlusIt = 3
            val objectToList = objects.associateWith {
                objects
                    .sortedBy { other -> it.distanceTo(other) }
                    .take(closestNeighborsPlusIt)
                    .filter { other -> it !== other }
                    .toSet()
            }

            return NeighborhoodGraph(filterOutlier(objectToList))
        }

        private fun <T> filterOutlier(map: Map<T, Set<T>>): Map<T, Set<T>> =
            map.filter { (point, neighbors) ->
                neighbors.all { neighbor -> map.getValue(neighbor).contains(point) }
            }

        fun fromPolarPoints(points: List<Point>): NeighborhoodGraph<Point> {
            val sortedPoints = points.sortedBy { it.angle }

            val pointToList =
                sortedPoints
                    .withIndex()
                    .associate { (index, point) ->
                        Pair(point, computeClosest(index, sortedPoints))
                    }

            return NeighborhoodGraph(filterOutlier(pointToList))
        }

        private fun computeClosest(index: Int, points: List<Point>): Set<Point> {
            val it = points[index % points.size]
            val halfSize = (points.size * 0.5).toInt()
            val forwardIterator = points.asCyclicSequence(index + 1, index + halfSize).iterator()
            val backwardIterator = points.asCyclicReversed(index - 1, index - halfSize).iterator()
            tailrec fun computeNextClosestRec(
                it: Point,
                closestPoint: Point?,
                distanceToClosest: Double,
                iterator: Iterator<Point>
            ): Point? {
                if (!iterator.hasNext()) return closestPoint
                val nextPoint = iterator.next()

                val shortestPossibleDistance = distanceOriginLineToPoint(nextPoint.angle, it)
                if (distanceToClosest < shortestPossibleDistance || it == nextPoint) return closestPoint

                val distanceToNext = distancePointToPoint(it, nextPoint)
                return if (distanceToClosest < distanceToNext)
                    computeNextClosestRec(it, closestPoint, distanceToClosest, iterator)
                else
                    computeNextClosestRec(it, nextPoint, distanceToNext, iterator)
            }
            return setOf(
                computeNextClosestRec(
                    it, null, Double.POSITIVE_INFINITY, forwardIterator
                )!!,
                computeNextClosestRec(
                    it, null, Double.POSITIVE_INFINITY, backwardIterator
                )!!
            )

        }
    }
}
