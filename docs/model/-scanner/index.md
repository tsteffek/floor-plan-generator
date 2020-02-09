[docs](../../index.md) / [model](../index.md) / [Scanner](./index.md)

# Scanner

`data class Scanner`

Data class to contain all the relevant information about a 2d radial lidar scanner.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Data class to contain all the relevant information about a 2d radial lidar scanner.`Scanner(id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, incremental: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, stepAngle: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`, clockwise: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, qualityMax: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, idKey: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "id", stepSizeKey: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "step size", distanceKey: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "distance", qualityKey: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "quality")` |

### Properties

| Name | Summary |
|---|---|
| [clockwise](clockwise.md) | whether the scanner rotates clockwise or counter-clockwise`val clockwise: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [distanceKey](distance-key.md) | the key for the distance-field in a tsv`val distanceKey: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [id](id.md) | an id`val id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [idKey](id-key.md) | the key for the id-field in a tsv`val idKey: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [incremental](incremental.md) | whether the scanner outputs incremental data, i.e. the data can't be seen as a total step size from an arbitrary starting line, but an incrementation of the last step.`val incremental: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [qualityKey](quality-key.md) | the key for the quality-field in a tsv`val qualityKey: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [qualityMax](quality-max.md) | the max value at which the quality isn't too bad`val qualityMax: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [stepAngle](step-angle.md) | the angle the scanner rotates per step`val stepAngle: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [stepSizeKey](step-size-key.md) | the key for the stepSize-field in a tsv`val stepSizeKey: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
