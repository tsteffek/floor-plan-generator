[docs](../../index.md) / [de.tsteffek.model.geometry](../index.md) / [LineSegment](./index.md)

# LineSegment

`class LineSegment : `[`Line`](../-line/index.md)

The [GeometricObject](../-geometric-object/index.md) resembling a line segment in slope-intercept-form,
with a start and end point.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `LineSegment(startPoint: `[`Point`](../-point/index.md)`, endPoint: `[`Point`](../-point/index.md)`)`<br>The [GeometricObject](../-geometric-object/index.md) resembling a line segment in slope-intercept-form, with a start and end point.`LineSegment(line: `[`Line`](../-line/index.md)`, startPoint: `[`Point`](../-point/index.md)`, endPoint: `[`Point`](../-point/index.md)`)` |

### Properties

| Name | Summary |
|---|---|
| [endPoint](end-point.md) | the endPoint`val endPoint: `[`Point`](../-point/index.md) |
| [startPoint](start-point.md) | the startPoint`val startPoint: `[`Point`](../-point/index.md) |

### Functions

| Name | Summary |
|---|---|
| [distanceTo](distance-to.md) | Computes the distance to another [GeometricObject](../-geometric-object/index.md).`fun distanceTo(other: `[`GeometricObject`](../-geometric-object/index.md)`): `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |

### Companion Object Functions

| Name | Summary |
|---|---|
| [fromSeveralPoints](from-several-points.md) | `fun fromSeveralPoints(points: `[`Collection`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/index.html)`<`[`PolarPoint`](../-polar-point/index.md)`>): `[`LineSegment`](./index.md) |
