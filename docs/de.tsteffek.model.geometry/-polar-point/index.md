[docs](../../index.md) / [de.tsteffek.model.geometry](../index.md) / [PolarPoint](./index.md)

# PolarPoint

`class PolarPoint : `[`Point`](../-point/index.md)

The [GeometricObject](../-geometric-object/index.md) resembling a 2D point expressed in polar coordinates.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `PolarPoint(angle: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, distance: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, quality: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`)`<br>`PolarPoint(angle: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, distance: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, quality: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`)`<br>`PolarPoint(angle: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, distance: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, quality: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`)`<br>The [GeometricObject](../-geometric-object/index.md) resembling a 2D point expressed in polar coordinates.`PolarPoint(angle: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, distance: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, quality: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`)` |

### Properties

| Name | Summary |
|---|---|
| [angle](angle.md) | the angle creating a line from the origin`val angle: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [distance](distance.md) | the distance along the line until this point.`val distance: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [quality](quality.md) | the quality of the measurement taken to produce this point (makes only sense for points coming from a lidar scanner)`val quality: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

### Functions

| Name | Summary |
|---|---|
| [normalizedDirection](normalized-direction.md) | The vector this point creates, but with length of 1.`fun normalizedDirection(): `[`Pair`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)`<`[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`>` |
| [rotateBy](rotate-by.md) | Rotates this [PolarPoint](./index.md) along the origin.`fun rotateBy(angle: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`): `[`PolarPoint`](./index.md) |
| [toString](to-string.md) | `fun toString(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [toTSVString](to-t-s-v-string.md) | Returns a [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) representation in form of tab-separated values.`fun toTSVString(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
