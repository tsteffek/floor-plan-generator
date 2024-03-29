[docs](../../index.md) / [de.tsteffek.model.geometry](../index.md) / [Point](./index.md)

# Point

`open class Point : `[`GeometricObject`](../-geometric-object/index.md)

The [GeometricObject](../-geometric-object/index.md) resembling a simple 2D point.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Point(x: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, y: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`)`<br>`Point(x: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, y: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`)`<br>`Point(x: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, y: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`)`<br>The [GeometricObject](../-geometric-object/index.md) resembling a simple 2D point.`Point(x: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, y: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`)` |

### Properties

| Name | Summary |
|---|---|
| [x](x.md) | location on the x-axis`val x: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [y](y.md) | location on the y-axis`val y: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |

### Functions

| Name | Summary |
|---|---|
| [distanceTo](distance-to.md) | Computes the distance to another [GeometricObject](../-geometric-object/index.md).`open fun distanceTo(other: `[`GeometricObject`](../-geometric-object/index.md)`): `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [equals](equals.md) | `open fun equals(other: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](hash-code.md) | `open fun hashCode(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [liesOn](lies-on.md) | Calculates whether this Point lies on the [line](lies-on.md#de.tsteffek.model.geometry.Point$liesOn(de.tsteffek.model.geometry.Line)/line).`fun liesOn(line: `[`Line`](../-line/index.md)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Calculates whether this Point lies on the [lineSeg](lies-on.md#de.tsteffek.model.geometry.Point$liesOn(de.tsteffek.model.geometry.LineSegment)/lineSeg).`fun liesOn(lineSeg: `[`LineSegment`](../-line-segment/index.md)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [minus](minus.md) | Classical vector subtraction.`operator fun minus(other: `[`Point`](./index.md)`): `[`Point`](./index.md) |
| [plus](plus.md) | Classical vector addition.`operator fun plus(other: `[`Point`](./index.md)`): `[`Point`](./index.md) |
| [times](times.md) | Classical scalar multiplication.`operator fun times(constant: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`): `[`Point`](./index.md) |
| [toString](to-string.md) | `open fun toString(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [toTSVString](to-t-s-v-string.md) | Returns a [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) representation in form of tab-separated values.`open fun toTSVString(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [PolarPoint](../-polar-point/index.md) | The [GeometricObject](../-geometric-object/index.md) resembling a 2D point expressed in polar coordinates.`class PolarPoint : `[`Point`](./index.md) |
