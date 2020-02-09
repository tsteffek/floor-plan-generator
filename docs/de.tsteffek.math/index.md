[docs](../index.md) / [de.tsteffek.math](./index.md)

## Package de.tsteffek.math

Contains various basic mathematical algorithms.

### Properties

| Name | Summary |
|---|---|
| [PIHALF](-p-i-h-a-l-f.md) | Precomputed value of PI / 2 for performance reasons.`const val PIHALF: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [PRECISION](-p-r-e-c-i-s-i-o-n.md) | Precision used for various geometric similarity checks.`const val PRECISION: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |

### Functions

| Name | Summary |
|---|---|
| [distanceLineToPoint](distance-line-to-point.md) | Calculates the shortest distance between a [Line](distance-line-to-point.md#de.tsteffek.math$distanceLineToPoint(de.tsteffek.model.geometry.Line, de.tsteffek.model.geometry.Point)/l) and a [Point](distance-line-to-point.md#de.tsteffek.math$distanceLineToPoint(de.tsteffek.model.geometry.Line, de.tsteffek.model.geometry.Point)/p).`fun distanceLineToPoint(l: `[`Line`](../de.tsteffek.model.geometry/-line/index.md)`, p: `[`Point`](../de.tsteffek.model.geometry/-point/index.md)`): `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [distanceOriginLineToPoint](distance-origin-line-to-point.md) | Calculates the shortest distance between a line going through the origin and [PolarPoint](distance-origin-line-to-point.md#de.tsteffek.math$distanceOriginLineToPoint(kotlin.Double, de.tsteffek.model.geometry.PolarPoint)/p).`fun distanceOriginLineToPoint(angle: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, p: `[`PolarPoint`](../de.tsteffek.model.geometry/-polar-point/index.md)`): `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [distancePointToPoint](distance-point-to-point.md) | Calculates the distance between two [Point](../de.tsteffek.model.geometry/-point/index.md)s [a](distance-point-to-point.md#de.tsteffek.math$distancePointToPoint(de.tsteffek.model.geometry.Point, de.tsteffek.model.geometry.Point)/a) and [b](distance-point-to-point.md#de.tsteffek.math$distancePointToPoint(de.tsteffek.model.geometry.Point, de.tsteffek.model.geometry.Point)/b).`fun distancePointToPoint(a: `[`Point`](../de.tsteffek.model.geometry/-point/index.md)`, b: `[`Point`](../de.tsteffek.model.geometry/-point/index.md)`): `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [intersectTwoLines](intersect-two-lines.md) | Calculates the [Point](../de.tsteffek.model.geometry/-point/index.md) of intersection between two [Line](../de.tsteffek.model.geometry/-line/index.md)s [a](intersect-two-lines.md#de.tsteffek.math$intersectTwoLines(de.tsteffek.model.geometry.Line, de.tsteffek.model.geometry.Line)/a) and [b](intersect-two-lines.md#de.tsteffek.math$intersectTwoLines(de.tsteffek.model.geometry.Line, de.tsteffek.model.geometry.Line)/b).`fun intersectTwoLines(a: `[`Line`](../de.tsteffek.model.geometry/-line/index.md)`, b: `[`Line`](../de.tsteffek.model.geometry/-line/index.md)`): `[`Point`](../de.tsteffek.model.geometry/-point/index.md) |
| [lengthOf](length-of.md) | Calculates the length of a 2-dimensional vector [[x](length-of.md#de.tsteffek.math$lengthOf(kotlin.Double, kotlin.Double)/x), [y](length-of.md#de.tsteffek.math$lengthOf(kotlin.Double, kotlin.Double)/y)].`fun lengthOf(x: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, y: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`): `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
