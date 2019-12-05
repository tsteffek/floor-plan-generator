package model

import java.util.concurrent.atomic.AtomicInteger

inline fun <T> Sequence<T>.filterAndCount(counter: AtomicInteger, crossinline predicate: (T) -> Boolean): Sequence<T> =
    this.filter {
        if (predicate(it)) {
            true
        } else {
            counter.incrementAndGet()
            false
        }
    }

fun <T : Any> List<T>.asCyclicSequence(startingIndex: Int, endIndex: Int): Sequence<T> {
    var i = startingIndex
    return generateSequence {
        if (i <= endIndex) this[Math.floorMod(i++, this.size)]
        else null
    }
}

fun <T : Any> List<T>.asCyclicReversed(startingIndex: Int, endIndex: Int): Sequence<T> {
    var i = startingIndex
    return generateSequence {
        if (i >= endIndex) this[Math.floorMod(i--, this.size)]
        else null
    }
}
