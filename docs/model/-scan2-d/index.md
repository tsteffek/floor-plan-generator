[docs](../../index.md) / [model](../index.md) / [Scan2D](./index.md)

# Scan2D

`class Scan2D`

An object containing the results of a radial 2D lidar scan.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | An object containing the results of a radial 2D lidar scan.`Scan2D(pointCloud: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`PolarPoint`](../../model.geometry/-polar-point/index.md)`>, scanner: `[`Scanner`](../-scanner/index.md)`)` |

### Properties

| Name | Summary |
|---|---|
| [pointCloud](point-cloud.md) | a [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html) of detected [PolarPoint](../../model.geometry/-polar-point/index.md)s`val pointCloud: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`PolarPoint`](../../model.geometry/-polar-point/index.md)`>` |

### Functions

| Name | Summary |
|---|---|
| [rotateBy](rotate-by.md) | Rotates all the points by [angle](rotate-by.md#model.Scan2D$rotateBy(kotlin.Double)/angle).`fun rotateBy(angle: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`PolarPoint`](../../model.geometry/-polar-point/index.md)`>` |
| [toTSV](to-t-s-v.md) | Returns this object as TSV [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html).`fun toTSV(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Companion Object Functions

| Name | Summary |
|---|---|
| [fromTSV](from-t-s-v.md) | Creates a [Scan2D](./index.md) from a tsv [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html).`fun fromTSV(tsvFile: `[`File`](https://docs.oracle.com/javase/8/docs/api/java/io/File.html)`, scanner: `[`Scanner`](../-scanner/index.md)`): `[`Scan2D`](./index.md)<br>Creates a [Scan2D](./index.md) from a [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) leading to a tsv file`fun fromTSV(tsvString: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, scanner: `[`Scanner`](../-scanner/index.md)`): `[`Scan2D`](./index.md) |
