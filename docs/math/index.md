[docs](../index.md) / [math](./index.md)

## Package math

### Properties

| Name | Summary |
|---|---|
| [PIHALF](-p-i-h-a-l-f.md) | Precomputed value of PI / 2 for performance reasons.`const val PIHALF: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [PRECISION](-p-r-e-c-i-s-i-o-n.md) | Precision used for various geometric similarity checks.`const val PRECISION: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |

### Functions

| Name | Summary |
|---|---|
| [distanceLineToPoint](distance-line-to-point.md) | Calculates the shortest distance between a [Line](distance-line-to-point.md#math$distanceLineToPoint(model.geometry.Line, model.geometry.Point)/l) and a [Point](distance-line-to-point.md#math$distanceLineToPoint(model.geometry.Line, model.geometry.Point)/p).`fun distanceLineToPoint(l: `[`Line`](../model.geometry/-line/index.md)`, p: `[`Point`](../model.geometry/-point/index.md)`): `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [distanceOriginLineToPoint](distance-origin-line-to-point.md) | Calculates the shortest distance between a line going through the origin and [PolarPoint](distance-origin-line-to-point.md#math$distanceOriginLineToPoint(kotlin.Double, model.geometry.PolarPoint)/p).`fun distanceOriginLineToPoint(angle: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, p: `[`PolarPoint`](../model.geometry/-polar-point/index.md)`): `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [distancePointToPoint](distance-point-to-point.md) | Calculates the distance between two [Point](../model.geometry/-point/index.md)s [a](distance-point-to-point.md#math$distancePointToPoint(model.geometry.Point, model.geometry.Point)/a) and [b](distance-point-to-point.md#math$distancePointToPoint(model.geometry.Point, model.geometry.Point)/b).`fun distancePointToPoint(a: `[`Point`](../model.geometry/-point/index.md)`, b: `[`Point`](../model.geometry/-point/index.md)`): `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [intersectTwoLines](intersect-two-lines.md) | Calculates the [Point](../model.geometry/-point/index.md) of intersection between two [Line](../model.geometry/-line/index.md)s [a](intersect-two-lines.md#math$intersectTwoLines(model.geometry.Line, model.geometry.Line)/a) and [b](intersect-two-lines.md#math$intersectTwoLines(model.geometry.Line, model.geometry.Line)/b).`fun intersectTwoLines(a: `[`Line`](../model.geometry/-line/index.md)`, b: `[`Line`](../model.geometry/-line/index.md)`): `[`Point`](../model.geometry/-point/index.md) |
| [lengthOf](length-of.md) | Calculates the length of a 2-dimensional vector [[x](length-of.md#math$lengthOf(kotlin.Double, kotlin.Double)/x), [y](length-of.md#math$lengthOf(kotlin.Double, kotlin.Double)/y)].`fun lengthOf(x: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, y: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`): `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
