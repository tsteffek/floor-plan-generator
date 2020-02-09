[docs](../index.md) / [algorithms](index.md) / [fitLines](./fit-lines.md)

# fitLines

`fun <T : `[`Point`](../model.geometry/-point/index.md)`> fitLines(graph: `[`NeighborhoodGraph`](../model/-neighborhood-graph/index.md)`<T>): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Set`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)`<T>>`

Tries to fit lines onto the [T](fit-lines.md#T)s in a [NeighborhoodGraph](fit-lines.md#algorithms$fitLines(model.NeighborhoodGraph((algorithms.fitLines.T)))/graph) using a very basic, one-iteration method (used as initialisation step for more complex algorithms).

**Return**
a list containing sets of points which have been fitted together (might contain sets with just one [T](fit-lines.md#T))

