package model

import math.distanceOriginLineToPoint
import math.distancePointToPoint
import model.geometry.GeometricObject
import model.geometry.PolarPoint

class NeighborhoodGraph<T : GeometricObject>(
    private val map: Map<T, Set<T>>
) {
    fun getObjects(): Set<T> = map.keys

    fun getNeighbors(t: T): Set<T> = map.getValue(t)

    companion object {
        fun <T : GeometricObject> usingBruteForce(
            objects: List<T>,
            closestNeighbors: Int = 2
        ): NeighborhoodGraph<T> {
            val objectToList = objects.associateWith {
                objects.asSequence()
                    .sortedBy { other -> it.distanceTo(other) }
                    .minus(it)
                    .take(closestNeighbors)
                    .filter { other -> it !== other }
                    .toSet()
            }

            return NeighborhoodGraph(filterOutlier(objectToList))
        }

        private fun <T> filterOutlier(map: Map<T, Set<T>>): Map<T, Set<T>> =
            map.filter { (point, neighbors) ->
                neighbors.all { neighbor -> map.getValue(neighbor).contains(point) }
            }

        fun fromPolarPoints(points: List<PolarPoint>): NeighborhoodGraph<PolarPoint> {
            val sortedPoints = points.sortedBy { it.angle }

            val pointToList =
                sortedPoints
                    .withIndex()
                    .associate { (index, point) ->
                        Pair(point, computeClosest(index, sortedPoints))
                    }

            return NeighborhoodGraph(filterOutlier(pointToList))
        }

        private fun computeClosest(index: Int, points: List<PolarPoint>): Set<PolarPoint> {
            val it = points[index % points.size]
            val halfSize = (points.size * 0.5).toInt()
            val forwardIterator = points.asCyclicSequence(index + 1, index + halfSize).iterator()
            val backwardIterator = points.asCyclicReversed(index - 1, index - halfSize).iterator()
            return setOf(
                computeNextClosestRec(
                    it, null, Double.POSITIVE_INFINITY, forwardIterator
                )!!,
                computeNextClosestRec(
                    it, null, Double.POSITIVE_INFINITY, backwardIterator
                )!!
            )

        }

        private tailrec fun computeNextClosestRec(
            it: PolarPoint,
            closestPoint: PolarPoint?,
            distanceToClosest: Double,
            iterator: Iterator<PolarPoint>
        ): PolarPoint? {
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
    }
}
