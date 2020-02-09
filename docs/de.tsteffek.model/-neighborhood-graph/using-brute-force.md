[docs](../../index.md) / [de.tsteffek.model](../index.md) / [NeighborhoodGraph](index.md) / [usingBruteForce](./using-brute-force.md)

# usingBruteForce

`fun <T : `[`GeometricObject`](../../de.tsteffek.model.geometry/-geometric-object/index.md)`> usingBruteForce(objects: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<T>, closestNeighbors: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 2): `[`NeighborhoodGraph`](index.md)`<T>`

Computes the [NeighborhoodGraph](index.md) of a [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html) of [T](using-brute-force.md#T)s using brute
force. Depending on the type of [T](using-brute-force.md#T), more efficient algorithms may be available.

### Parameters

`closestNeighbors` - amount of closest neighbors to detect,
defaults to 2