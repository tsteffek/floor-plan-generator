package de.tsteffek.model

import de.tsteffek.math.distance
import de.tsteffek.math.distanceOriginLineToPoint
import de.tsteffek.model.geometry.GeometricObject
import de.tsteffek.model.geometry.PolarPoint
import mu.KotlinLogging
import java.util.concurrent.atomic.LongAdder

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
         * force. Depending on the type of [T], more efficient algorithms may
         * be available.
         * @param numberClosestNeighbors amount of closest neighbors to detect,
         * defaults to 2
         */
        fun <T : GeometricObject> fromBruteForce(
            objects: List<T>,
            numberClosestNeighbors: Int = 2
        ): NeighborhoodGraph<T> {
            val objectToList = objects.associateWith {
                objects.asSequence()
                    .sortedBy { other -> it.distanceTo(other) }
                    .minus(it)
                    .take(numberClosestNeighbors)
                    .toSet()
            }

            return NeighborhoodGraph(filterOutlier(objectToList))
        }

        private fun <T> filterOutlier(map: Map<T, Set<T>>): Map<T, Set<T>> {
            val count = LongAdder()
            val filteredMap = map.filterAndCount(count) { (point, neighbors) ->
                neighbors.any { neighbor ->
                    val neighborsOfNeighbor = map.getValue(neighbor)
                    neighborsOfNeighbor.contains(point)
                }
            }
            logger.info { "filtered outliers: ${count.sum()} out of ${map.size}" }
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
                        Pair(point, computeClosest(index, sortedPoints))
                    }

            return NeighborhoodGraph(filterOutlier(pointToList))
        }

        private fun computeClosest(index: Int, points: List<PolarPoint>): Set<PolarPoint> {
            val it = points[index]
            val sizeWithoutIt = points.size - 1
            val halfSize = sizeWithoutIt / 2
            val oddNumberRemainder = sizeWithoutIt % 2

            val closestForwardsNeighbor = computeClosestForwards(
                points, index + 1, halfSize + oddNumberRemainder, it
            )
            val closestBackwardsNeighbor = computeClosestBackwards(
                points, index - 1, halfSize, it
            )

            return setOf(closestForwardsNeighbor, closestBackwardsNeighbor)
        }

        private fun computeClosestForwards(
            points: List<PolarPoint>,
            startIndex: Int,
            elementsToIterate: Int,
            it: PolarPoint
        ): PolarPoint {
            val forwardIterator =
                points.asCyclicSequence(startIndex, startIndex + elementsToIterate).iterator()
            return computeNextClosestRec(it, forwardIterator)!!
        }

        private fun computeClosestBackwards(
            points: List<PolarPoint>,
            startIndex: Int,
            elementsToIterate: Int,
            it: PolarPoint
        ): PolarPoint {
            val backwardIterator =
                points.asCyclicReversed(startIndex, startIndex - elementsToIterate).iterator()
            return computeNextClosestRec(it, backwardIterator)!!
        }

        @Suppress("TAILREC_WITH_DEFAULTS")
        private tailrec fun computeNextClosestRec(
            it: PolarPoint,
            iterator: Iterator<PolarPoint>,
            closestPoint: PolarPoint? = null,
            distanceToClosest: Double = Double.POSITIVE_INFINITY
        ): PolarPoint? {
            if (!iterator.hasNext()) return closestPoint
            val nextPoint = iterator.next()

            val shortestPossibleDistance = distanceOriginLineToPoint(nextPoint.angle, it)
            if (distanceToClosest < shortestPossibleDistance || it == nextPoint) return closestPoint

            val distanceToNext = distance(it, nextPoint)
            return if (distanceToClosest < distanceToNext)
                computeNextClosestRec(it, iterator, closestPoint, distanceToClosest)
            else
                computeNextClosestRec(it, iterator, nextPoint, distanceToNext)
        }
    }
}
