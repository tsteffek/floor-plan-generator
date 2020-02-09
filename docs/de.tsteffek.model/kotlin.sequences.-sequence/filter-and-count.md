[docs](../../index.md) / [de.tsteffek.model](../index.md) / [kotlin.sequences.Sequence](index.md) / [filterAndCount](./filter-and-count.md)

# filterAndCount

`inline fun <T> `[`Sequence`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/index.html)`<T>.filterAndCount(counter: `[`AtomicInteger`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/AtomicInteger.html)`, crossinline predicate: (T) -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Sequence`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/index.html)`<T>`

Returns a sequence containing only elements matching the given [predicate](filter-and-count.md#de.tsteffek.model$filterAndCount(kotlin.sequences.Sequence((de.tsteffek.model.filterAndCount.T)), java.util.concurrent.atomic.AtomicInteger, kotlin.Function1((de.tsteffek.model.filterAndCount.T, kotlin.Boolean)))/predicate)
while counting every *mismatch*.

The operation is *intermediate* and *stateless*.

### Parameters

`counter` - will contain the amount of filtered (thus removed) elements
afterwards

**Receiver**
[Sequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/index.html)&lt;[T](filter-and-count.md#T)&gt;

