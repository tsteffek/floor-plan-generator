[docs](../../index.md) / [model.geometry](../index.md) / [GeometricObject](./index.md)

# GeometricObject

`interface GeometricObject`

A geometric object.

### Functions

| Name | Summary |
|---|---|
| [distanceTo](distance-to.md) | Computes the distance to another [GeometricObject](./index.md).`abstract fun distanceTo(other: `[`GeometricObject`](./index.md)`): `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [toTSVString](to-t-s-v-string.md) | Returns a [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) representation in form of tab-separated values.`abstract fun toTSVString(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [Line](../-line/index.md) | The [GeometricObject](./index.md) resembling a simple line in slope-intercept-form.`class Line : `[`GeometricObject`](./index.md) |
| [Point](../-point/index.md) | The [GeometricObject](./index.md) resembling a simple 2D point.`open class Point : `[`GeometricObject`](./index.md) |
