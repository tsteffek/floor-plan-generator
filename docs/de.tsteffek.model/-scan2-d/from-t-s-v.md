[docs](../../index.md) / [de.tsteffek.model](../index.md) / [Scan2D](index.md) / [fromTSV](./from-t-s-v.md)

# fromTSV

`fun fromTSV(tsvFile: `[`File`](https://docs.oracle.com/javase/8/docs/api/java/io/File.html)`, scanner: `[`Scanner`](../-scanner/index.md)`): `[`Scan2D`](index.md)

Creates a [Scan2D](index.md) from a tsv [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html).

Assumes a header line with the values matching the keys in the
scanner.

### Parameters

`scanner` - the [Scanner](../-scanner/index.md) used to create the data`fun fromTSV(tsvString: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, scanner: `[`Scanner`](../-scanner/index.md)`): `[`Scan2D`](index.md)

Creates a [Scan2D](index.md) from a [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) leading to a tsv file

Assumes a header line with the values matching the keys in the
scanner.

### Parameters

`scanner` - the [Scanner](../-scanner/index.md) used to create the data