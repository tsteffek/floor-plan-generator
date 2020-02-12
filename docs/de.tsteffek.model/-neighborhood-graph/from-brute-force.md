[docs](../../index.md) / [de.tsteffek.model](../index.md) / [NeighborhoodGraph](index.md) / [fromBruteForce](./from-brute-force.md)

# fromBruteForce

`fun <T : `[`GeometricObject`](../../de.tsteffek.model.geometry/-geometric-object/index.md)`> fromBruteForce(objects: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<T>, numberClosestNeighbors: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 2): `[`NeighborhoodGraph`](index.md)`<T>`

Computes the [NeighborhoodGraph](index.md) of a [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html) of [T](from-brute-force.md#T)s using brute
force. Depending on the type of [T](from-brute-force.md#T), more efficient algorithms may
be available.

### Parameters

`numberClosestNeighbors` - amount of closest neighbors to detect,
defaults to 2