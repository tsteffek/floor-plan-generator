[docs](../index.md) / [de.tsteffek.model](index.md) / [filterAndCount](./filter-and-count.md)

# filterAndCount

`inline fun <T> filterAndCount(counter: `[`LongAdder`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/LongAdder.html)`, crossinline predicate: (T) -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): (T) -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Higher order galore: Takes functions, returns functions.
It works, but isn't as clean as my original approach in my opinion.

