[docs](../index.md) / [de.tsteffek.model](./index.md)

## Package de.tsteffek.model

Contains various classes to model the task of model fitting.

### Types

| Name | Summary |
|---|---|
| [NeighborhoodGraph](-neighborhood-graph/index.md) | A NeighborhoodGraph of [GeometricObject](../de.tsteffek.model.geometry/-geometric-object/index.md)s.`data class NeighborhoodGraph<T : `[`GeometricObject`](../de.tsteffek.model.geometry/-geometric-object/index.md)`>` |
| [Scan2D](-scan2-d/index.md) | An object containing the results of a radial 2D lidar scan.`class Scan2D` |
| [Scanner](-scanner/index.md) | Data class to contain all the relevant information about a 2d radial lidar scanner.`data class Scanner` |

### Extensions for External Classes

| Name | Summary |
|---|---|
| [kotlin.collections.Collection](kotlin.collections.-collection/index.md) |  |
| [kotlin.collections.List](kotlin.collections.-list/index.md) |  |
| [kotlin.collections.Map](kotlin.collections.-map/index.md) |  |
| [kotlin.sequences.Sequence](kotlin.sequences.-sequence/index.md) |  |

### Functions

| Name | Summary |
|---|---|
| [filterAndCount](filter-and-count.md) | Higher order galore: Takes functions, returns functions. It works, but isn't as clean as my original approach in my opinion.`fun <T> filterAndCount(counter: `[`LongAdder`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/LongAdder.html)`, predicate: (T) -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): (T) -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
