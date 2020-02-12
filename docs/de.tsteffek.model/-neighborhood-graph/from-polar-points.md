[docs](../../index.md) / [de.tsteffek.model](../index.md) / [NeighborhoodGraph](index.md) / [fromPolarPoints](./from-polar-points.md)

# fromPolarPoints

`fun fromPolarPoints(points: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`PolarPoint`](../../de.tsteffek.model.geometry/-polar-point/index.md)`>): `[`NeighborhoodGraph`](index.md)`<`[`PolarPoint`](../../de.tsteffek.model.geometry/-polar-point/index.md)`>`

Computes a [NeighborhoodGraph](index.md) utilizing inherent properties of
[PolarPoint](../../de.tsteffek.model.geometry/-polar-point/index.md)s.

**Return**
a graph with exactly 2 neighbors per [PolarPoint](../../de.tsteffek.model.geometry/-polar-point/index.md), which are
the closest neighbors *per side*.

