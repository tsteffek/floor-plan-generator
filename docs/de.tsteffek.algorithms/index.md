[docs](../index.md) / [de.tsteffek.algorithms](./index.md)

## Package de.tsteffek.algorithms

Contains various algorithms used for model fitting.

### Properties

| Name | Summary |
|---|---|
| [THRESHOLD](-t-h-r-e-s-h-o-l-d.md) | Threshold for line fitting, defaults to 1mm deviation from the line.`const val THRESHOLD: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |

### Functions

| Name | Summary |
|---|---|
| [fitLines](fit-lines.md) | Tries to fit lines onto the [T](fit-lines.md#T)s in a [NeighborhoodGraph](fit-lines.md#de.tsteffek.algorithms$fitLines(de.tsteffek.model.NeighborhoodGraph((de.tsteffek.algorithms.fitLines.T)))/graph) using a very basic, one-iteration method (used as initialisation step for more complex algorithms).`fun <T : `[`Point`](../de.tsteffek.model.geometry/-point/index.md)`> fitLines(graph: `[`NeighborhoodGraph`](../de.tsteffek.model/-neighborhood-graph/index.md)`<T>): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Set`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)`<T>>` |
