[docs](../../index.md) / [de.tsteffek.model.geometry](../index.md) / [Line](./index.md)

# Line

`class Line : `[`GeometricObject`](../-geometric-object/index.md)

The [GeometricObject](../-geometric-object/index.md) resembling a simple line in slope-intercept-form.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Line(slope: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, intercept: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`)`<br>`Line(slope: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, intercept: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`)`<br>`Line(slope: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, intercept: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`)`<br>The [GeometricObject](../-geometric-object/index.md) resembling a simple line in slope-intercept-form.`Line(slope: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, intercept: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`)` |

### Properties

| Name | Summary |
|---|---|
| [intercept](intercept.md) | y-value of the interception point of this line and the y-axis`val intercept: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [slope](slope.md) | the slope of the line, i.e. the increase along the y-axis per step along the x-axis.`val slope: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |

### Functions

| Name | Summary |
|---|---|
| [distanceTo](distance-to.md) | Computes the distance to another [GeometricObject](../-geometric-object/index.md).`fun distanceTo(other: `[`GeometricObject`](../-geometric-object/index.md)`): `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [equals](equals.md) | `fun equals(other: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](hash-code.md) | `fun hashCode(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](to-string.md) | `fun toString(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [toTSVString](to-t-s-v-string.md) | Returns a [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) representation in form of tab-separated values.`fun toTSVString(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Companion Object Functions

| Name | Summary |
|---|---|
| [fromSeveralPoints](from-several-points.md) | Fits a line between several [points](from-several-points.md#de.tsteffek.model.geometry.Line.Companion$fromSeveralPoints(kotlin.collections.Collection((de.tsteffek.model.geometry.Point)))/points). Algorithm used: Least Squares following https://www.varsitytutors.com/hotmath/hotmath_help/topics/line-of-best-fit`fun fromSeveralPoints(points: `[`Collection`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/index.html)`<`[`Point`](../-point/index.md)`>): `[`Line`](./index.md) |
| [fromTwoPoints](from-two-points.md) | Computes the line between two [Point](../-point/index.md)s [a](from-two-points.md#de.tsteffek.model.geometry.Line.Companion$fromTwoPoints(de.tsteffek.model.geometry.Point, de.tsteffek.model.geometry.Point)/a) and [b](from-two-points.md#de.tsteffek.model.geometry.Line.Companion$fromTwoPoints(de.tsteffek.model.geometry.Point, de.tsteffek.model.geometry.Point)/b).`fun fromTwoPoints(a: `[`Point`](../-point/index.md)`, b: `[`Point`](../-point/index.md)`): `[`Line`](./index.md) |
