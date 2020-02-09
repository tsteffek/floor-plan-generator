[docs](../index.md) / [de.tsteffek.io](index.md) / [readFromTSV](./read-from-t-s-v.md)

# readFromTSV

`fun readFromTSV(tsv: `[`File`](https://docs.oracle.com/javase/8/docs/api/java/io/File.html)`): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>`

Reads the given [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html) as TSV, assumes a header line.

### Parameters

`tsv` - a [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html) containing tab-separated values

**Return**
a [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html) of [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)s which represent a single line respectively

`fun readFromTSV(tsv: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>`

Reads the given [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) as TSV file, assumes a header line.

### Parameters

`tsv` - a [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) with the path and name to a file containing
tab-separated values

**Return**
a [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html) of [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)s which represent a single line respectively

