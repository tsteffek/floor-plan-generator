[docs](../../index.md) / [de.tsteffek.model](../index.md) / [NeighborhoodGraph](./index.md)

# NeighborhoodGraph

`data class NeighborhoodGraph<T : `[`GeometricObject`](../../de.tsteffek.model.geometry/-geometric-object/index.md)`>`

A NeighborhoodGraph of [GeometricObject](../../de.tsteffek.model.geometry/-geometric-object/index.md)s.

### Parameters

`T` - type of the [GeometricObject](../../de.tsteffek.model.geometry/-geometric-object/index.md)s in this graph

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | accepts a precomputed graph`NeighborhoodGraph(map: `[`Map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)`<T, `[`Set`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)`<T>>)` |

### Functions

| Name | Summary |
|---|---|
| [getNeighbors](get-neighbors.md) | Returns the closest neighbors to [T](index.md#T). Size may vary depending on how the graph was created.`fun getNeighbors(t: T): `[`Set`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)`<T>` |
| [getObjects](get-objects.md) | Returns the points in this graph.`fun getObjects(): `[`Set`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)`<T>` |
| [getSize](get-size.md) | Returns the amount of points in this graph.`fun getSize(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

### Companion Object Functions

| Name | Summary |
|---|---|
| [fromBruteForce](from-brute-force.md) | Computes the [NeighborhoodGraph](./index.md) of a [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html) of [T](from-brute-force.md#T)s using brute force. Depending on the type of [T](from-brute-force.md#T), more efficient algorithms may be available.`fun <T : `[`GeometricObject`](../../de.tsteffek.model.geometry/-geometric-object/index.md)`> fromBruteForce(objects: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<T>, numberClosestNeighbors: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 2): `[`NeighborhoodGraph`](./index.md)`<T>` |
| [fromPolarPoints](from-polar-points.md) | Computes a [NeighborhoodGraph](./index.md) utilizing inherent properties of [PolarPoint](../../de.tsteffek.model.geometry/-polar-point/index.md)s.`fun fromPolarPoints(points: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`PolarPoint`](../../de.tsteffek.model.geometry/-polar-point/index.md)`>): `[`NeighborhoodGraph`](./index.md)`<`[`PolarPoint`](../../de.tsteffek.model.geometry/-polar-point/index.md)`>` |
