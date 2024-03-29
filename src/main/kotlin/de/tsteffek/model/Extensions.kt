package de.tsteffek.model

import java.util.concurrent.atomic.LongAdder

/**
 * Returns a sequence containing only elements matching the given [predicate]
 * while counting every *mismatch*.
 *
 * The operation is _intermediate_ and _stateless_.
 * @param counter will contain the amount of filtered (thus removed) elements
 * afterwards
 * @receiver [Sequence]<[T]>
 */
inline fun <T> Sequence<T>.filterAndCount(
    counter: LongAdder, crossinline predicate: (T) -> Boolean
): Sequence<T> =
    this.filter {
        if (predicate(it)) {
            true
        } else {
            counter.increment()
            false
        }
    }

/** Higher order galore: Takes functions, returns functions.
 * It works, but isn't as clean as my original approach in my opinion. */
inline fun <T> filterAndCount(
    counter: LongAdder, crossinline predicate: (T) -> Boolean
): (T) -> Boolean = {
    if (predicate(it)) {
        true
    } else {
        counter.increment()
        false
    }
}

/**
 * Returns a new map containing all key-value pairs matching the given
 * [predicate] while counting every *mismatch*
 *
 * The returned map preserves the entry iteration order of the original map.
 * @param counter will contain the amount of filtered (thus removed) elements
 * afterwards
 * @receiver [Map]<out [K], [V]>
 */
inline fun <K, V> Map<out K, V>.filterAndCount(
    counter: LongAdder, predicate: (Map.Entry<K, V>) -> Boolean
): Map<K, V> =
    this.filter {
        if (predicate(it)) {
            true
        } else {
            counter.increment()
            false
        }
    }

/**
 * Creates a [Sequence] instance that wraps the original collection returning
 * its elements when being iterated.
 *
 * Will cycle if necessary.
 * @param startingIndex index to start at \[inclusive\]
 * @param endIndex index to stop at \[exclusive\]
 * @receiver [List]<[T]>
 */
fun <T : Any> List<T>.asCyclicSequence(startingIndex: Int, endIndex: Int): Sequence<T> {
    var i = startingIndex
    return generateSequence {
        if (i < endIndex) this[Math.floorMod(i++, this.size)]
        else null
    }
}

/**
 * Creates a [Sequence] instance that wraps the original collection returning
 * its elements in reversed order when being iterated.
 *
 * Will cycle if necessary.
 * @param startingIndex index to start at \[inclusive\]
 * @param endIndex index to stop at \[exclusive\]
 * @receiver [List]<[T]>
 */
fun <T : Any> List<T>.asCyclicReversed(startingIndex: Int, endIndex: Int): Sequence<T> {
    var i = startingIndex
    return generateSequence {
        if (i > endIndex) this[Math.floorMod(i--, this.size)]
        else null
    }
}

/**
 * Calculates the arithmetic mean.
 * @receiver [Collection]<[Double]>
 */
fun Collection<Double>.mean(): Double = this.sum() / this.size
