[docs](../../index.md) / [model](../index.md) / [kotlin.collections.Map](index.md) / [filterAndCount](./filter-and-count.md)

# filterAndCount

`inline fun <K, V> `[`Map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)`<out K, V>.filterAndCount(counter: `[`AtomicInteger`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/AtomicInteger.html)`, predicate: (`[`Entry`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/-entry/index.html)`<K, V>) -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)`<K, V>`

Returns a new map containing all key-value pairs matching the given [predicate](filter-and-count.md#model$filterAndCount(kotlin.collections.Map((model.filterAndCount.K, model.filterAndCount.V)), java.util.concurrent.atomic.AtomicInteger, kotlin.Function1((kotlin.collections.Map.Entry((model.filterAndCount.K, model.filterAndCount.V)), kotlin.Boolean)))/predicate) while counting every *mismatch*

The returned map preserves the entry iteration order of the original map.

### Parameters

`counter` - will contain the amount of filtered (thus removed) elements afterwards

**Receiver**
[Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;out [K](filter-and-count.md#K), [V](filter-and-count.md#V)&gt;

