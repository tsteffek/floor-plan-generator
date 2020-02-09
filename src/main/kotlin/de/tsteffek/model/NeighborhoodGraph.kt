package de.tsteffek.model

import de.tsteffek.math.distanceOriginLineToPoint
import de.tsteffek.math.distancePointToPoint
import de.tsteffek.model.geometry.GeometricObject
import de.tsteffek.model.geometry.PolarPoint
import mu.KotlinLogging
import java.util.concurrent.atomic.AtomicInteger

private val logger by lazy { KotlinLogging.logger {} }

/**
 * A NeighborhoodGraph of [GeometricObject]s.
 * @param T type of the [GeometricObject]s in this graph
 * @property map a map of [T]s and their closest neighbors
 * @constructor accepts a precomputed graph
 */
data class NeighborhoodGraph<T : GeometricObject>(
    private val map: Map<T, Set<T>>
) {
    /** Returns the amount of points in this graph. */
    fun getSize(): Int = map.size

    /** Returns the points in this graph. */
    fun getObjects(): Set<T> = map.keys

    /** Returns the closest neighbors to [T]. Size may vary depending on how the
     * graph was created. */
    fun getNeighbors(t: T): Set<T> = map.getValue(t)

    companion object {
        /**
         * Computes the [NeighborhoodGraph] of a [List] of [T]s using brute
         * force. Depending on the type of [T], more efficient algorithms may be available.
         * @param closestNeighbors amount of closest neighbors to detect,
         * defaults to 2
         */
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

            return NeighborhoodGraph(
                filterOutlier(
                    objectToList
                )
            )
        }

        private fun <T> filterOutlier(map: Map<T, Set<T>>): Map<T, Set<T>> {
            val count = AtomicInteger(0)
            val filteredMap = map.filterAndCount(count) { (point, neighbors) ->
                neighbors.any { neighbor ->
                    map.getValue(neighbor).contains(point)
                }
            }
            logger.info { "filtered outliers: $count out of ${map.size}" }
            return filteredMap
        }

        /**
         * Computes a [NeighborhoodGraph] utilizing inherent properties of
         * [PolarPoint]s.
         * @return a graph with exactly 2 neighbors per [PolarPoint], which are
         * the closest neighbors *per side*.
         */
        fun fromPolarPoints(points: List<PolarPoint>): NeighborhoodGraph<PolarPoint> {
            val sortedPoints = points.sortedBy { it.angle }

            val pointToList =
                sortedPoints
                    .withIndex()
                    .associate { (index, point) ->
                        Pair(point,
                            computeClosest(
                                index,
                                sortedPoints
                            )
                        )
                    }

            return NeighborhoodGraph(
                filterOutlier(
                    pointToList
                )
            )
        }

        private fun computeClosest(index: Int, points: List<PolarPoint>): Set<PolarPoint> {
            val it = points[index % points.size]
            val halfSize = (points.size * 0.5).toInt()
            val forwardIterator =
                points.asCyclicSequence(index + 1, index + halfSize + points.size % 2).iterator()
            val backwardIterator =
                points.asCyclicReversed(index - 1, index - halfSize).iterator()
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

            val shortestPossibleDistance =
                distanceOriginLineToPoint(nextPoint.angle, it)
            if (distanceToClosest < shortestPossibleDistance || it == nextPoint) return closestPoint

            val distanceToNext = distancePointToPoint(it, nextPoint)
            return if (distanceToClosest < distanceToNext)
                computeNextClosestRec(
                    it,
                    closestPoint,
                    distanceToClosest,
                    iterator
                )
            else
                computeNextClosestRec(
                    it,
                    nextPoint,
                    distanceToNext,
                    iterator
                )
        }
    }
}
