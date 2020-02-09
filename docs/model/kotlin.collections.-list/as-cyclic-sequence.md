[docs](../../index.md) / [model](../index.md) / [kotlin.collections.List](index.md) / [asCyclicSequence](./as-cyclic-sequence.md)

# asCyclicSequence

`fun <T : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`> `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<T>.asCyclicSequence(startingIndex: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, endIndex: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Sequence`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/index.html)`<T>`

Creates a [Sequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/index.html) instance that wraps the original collection returning its elements when being iterated. Will cycle if necessary.

### Parameters

`startingIndex` - index to start at \[inclusive\]

`endIndex` - index to stop at \[inclusive\]

**Receiver**
[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[T](as-cyclic-sequence.md#T)&gt;

