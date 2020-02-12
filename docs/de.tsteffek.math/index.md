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
| [distance](distance.md) | Calculates the distance between two [Point](../de.tsteffek.model.geometry/-point/index.md)s [a](distance.md#de.tsteffek.math$distance(de.tsteffek.model.geometry.Point, de.tsteffek.model.geometry.Point)/a) and [b](distance.md#de.tsteffek.math$distance(de.tsteffek.model.geometry.Point, de.tsteffek.model.geometry.Point)/b).`fun distance(a: `[`Point`](../de.tsteffek.model.geometry/-point/index.md)`, b: `[`Point`](../de.tsteffek.model.geometry/-point/index.md)`): `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Calculates the shortest distance between a [Point](distance.md#de.tsteffek.math$distance(de.tsteffek.model.geometry.Point, de.tsteffek.model.geometry.Line)/p) and a [Line](distance.md#de.tsteffek.math$distance(de.tsteffek.model.geometry.Point, de.tsteffek.model.geometry.Line)/l).`fun distance(p: `[`Point`](../de.tsteffek.model.geometry/-point/index.md)`, l: `[`Line`](../de.tsteffek.model.geometry/-line/index.md)`): `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Calculates the distance between a [Point](distance.md#de.tsteffek.math$distance(de.tsteffek.model.geometry.Point, de.tsteffek.model.geometry.LineSegment)/p) and a [LineSegment](distance.md#de.tsteffek.math$distance(de.tsteffek.model.geometry.Point, de.tsteffek.model.geometry.LineSegment)/lineSeg).`fun distance(p: `[`Point`](../de.tsteffek.model.geometry/-point/index.md)`, lineSeg: `[`LineSegment`](../de.tsteffek.model.geometry/-line-segment/index.md)`): `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Calculates the shortest distance between two [LineSegment](../de.tsteffek.model.geometry/-line-segment/index.md)s [lineA](distance.md#de.tsteffek.math$distance(de.tsteffek.model.geometry.LineSegment, de.tsteffek.model.geometry.LineSegment)/lineA) and [lineB](distance.md#de.tsteffek.math$distance(de.tsteffek.model.geometry.LineSegment, de.tsteffek.model.geometry.LineSegment)/lineB).`fun distance(lineA: `[`LineSegment`](../de.tsteffek.model.geometry/-line-segment/index.md)`, lineB: `[`LineSegment`](../de.tsteffek.model.geometry/-line-segment/index.md)`): `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [distanceOriginLineToPoint](distance-origin-line-to-point.md) | Calculates the shortest distance between a line going through the origin and [PolarPoint](distance-origin-line-to-point.md#de.tsteffek.math$distanceOriginLineToPoint(kotlin.Double, de.tsteffek.model.geometry.PolarPoint)/p).`fun distanceOriginLineToPoint(angle: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, p: `[`PolarPoint`](../de.tsteffek.model.geometry/-polar-point/index.md)`): `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
