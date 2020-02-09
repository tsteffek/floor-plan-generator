[docs](../../index.md) / [de.tsteffek.model.geometry](../index.md) / [PolarPoint](./index.md)

# PolarPoint

`class PolarPoint : `[`Point`](../-point/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `PolarPoint(angle: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, distance: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, quality: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`)`<br>`PolarPoint(angle: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, distance: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, quality: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`)`<br>`PolarPoint(angle: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, distance: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, quality: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`)`<br>`PolarPoint(angle: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, distance: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, quality: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`)` |

### Properties

| Name | Summary |
|---|---|
| [angle](angle.md) | `val angle: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [distance](distance.md) | `val distance: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [quality](quality.md) | `val quality: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

### Functions

| Name | Summary |
|---|---|
| [normalizedDirection](normalized-direction.md) | `fun normalizedDirection(): `[`Pair`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)`<`[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`>` |
| [rotateBy](rotate-by.md) | `fun rotateBy(angle: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`): `[`PolarPoint`](./index.md) |
| [toString](to-string.md) | `fun toString(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [toTSVString](to-t-s-v-string.md) | Returns a [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) representation in form of tab-separated values.`fun toTSVString(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
