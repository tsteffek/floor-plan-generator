[docs](../../index.md) / [de.tsteffek.model](../index.md) / [kotlin.collections.List](index.md) / [asCyclicReversed](./as-cyclic-reversed.md)

# asCyclicReversed

`fun <T : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`> `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<T>.asCyclicReversed(startingIndex: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, endIndex: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Sequence`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/index.html)`<T>`

Creates a [Sequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/index.html) instance that wraps the original collection returning
its elements in reversed order when being iterated.

Will cycle if necessary.

### Parameters

`startingIndex` - index to start at \[inclusive\]

`endIndex` - index to stop at \[inclusive\]

**Receiver**
[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[T](as-cyclic-reversed.md#T)&gt;

