package de.tsteffek.model

/**
 * Data class to contain all the relevant information about a 2d radial lidar
 * scanner.
 * @property id an id
 * @property incremental whether the scanner outputs incremental data, i.e. the
 * data can't be seen as a total step size from an arbitrary starting line, but an incrementation of the last step.
 * @property stepAngle the angle the scanner rotates per step
 * @property clockwise whether the scanner rotates clockwise or counter-clockwise
 * @property qualityMax the max value at which the quality isn't too bad
 * @property tsvKeys a wrapper class for the 4 tsv keys, all for the sake of building a DSL
 */
data class Scanner(
    internal val id: String,
    internal val incremental: Boolean,
    internal val stepAngle: Double,
    internal val clockwise: Boolean,
    internal val qualityMax: Int,
    internal val tsvKeys: TsvKeys = TsvKeys()
)


/**
 * Originally belongs to [Scanner], but are moved here for the sake of building a DSL.
 * @property idKey the key for the id-field in a tsv
 * @property stepSizeKey the key for the stepSize-field in a tsv
 * @property distanceKey the key for the distance-field in a tsv
 * @property qualityKey the key for the quality-field in a tsv
 */
data class TsvKeys(
    internal val idKey: String = "id",
    internal val stepSizeKey: String = "step size",
    internal val distanceKey: String = "distance",
    internal val qualityKey: String = "quality"
)

@DslMarker
annotation class ScannerDSL

fun scanner(lambda: ScannerBuilder.() -> Unit) = ScannerBuilder().apply(lambda).build()
fun notBuiltScanner(lambda: ScannerBuilder.() -> Unit) = ScannerBuilder().apply(lambda)

@ScannerDSL
class ScannerBuilder {
    internal var id = ""
    internal var incremental = false
    internal var stepAngle = 0.0
    internal var clockwise = true
    internal var qualityMax = 100

    private var tsvKeys = TsvKeys("", "", "", "")

    fun id(lambda: () -> String) {
        this.id = lambda()
    }

    fun incremental(lambda: () -> Boolean) {
        this.incremental = lambda()
    }

    fun stepAngle(lambda: () -> Double) {
        this.stepAngle = lambda()
    }

    fun clockwise(lambda: () -> Boolean) {
        this.clockwise = lambda()
    }

    fun qualityMax(lambda: () -> Int) {
        this.qualityMax = lambda()
    }

    fun tsvKeys(lambda: TsvKeysBuilder.() -> Unit) {
        tsvKeys = TsvKeysBuilder().apply(lambda).build()
    }

    infix fun withId(lambda: () -> String): ScannerBuilder {
        this.id = lambda()
        return this
    }

    infix fun withIncremental(bool: Boolean): ScannerBuilder {
        this.incremental = bool
        return this
    }

    infix fun withStepAngle(lambda: () -> Double): ScannerBuilder {
        this.stepAngle = lambda()
        return this
    }

    infix fun withClockwise(lambda: () -> Boolean): ScannerBuilder {
        this.clockwise = lambda()
        return this
    }

    infix fun withQualityMax(lambda: () -> Int): ScannerBuilder {
        this.qualityMax = lambda()
        return this
    }

    infix fun withTsvKeys(lambda: TsvKeysBuilder.() -> Unit): Scanner {
        tsvKeys = TsvKeysBuilder().apply(lambda).build()
        return build()
    }

    fun build() = Scanner(id, incremental, stepAngle, clockwise, qualityMax, tsvKeys)
}

@ScannerDSL
class TsvKeysBuilder {
    private var idKey: String = "id"
    private var stepSizeKey: String = "step size"
    private var distanceKey: String = "distance"
    private var qualityKey: String = "quality"

    fun idKey(lambda: () -> String) {
        this.idKey = lambda()
    }

    fun stepSizeKey(lambda: () -> String) {
        this.stepSizeKey = lambda()
    }

    fun distanceKey(lambda: () -> String) {
        this.distanceKey = lambda()
    }

    fun qualityKey(lambda: () -> String) {
        this.qualityKey = lambda()
    }

    fun build() = TsvKeys(idKey, stepSizeKey, distanceKey, qualityKey)
}

fun main() {
    // With this DSL, we can do stuff like
    val dslScanner = scanner {
        id { "ScannerId" }
        incremental { true }
        stepAngle { 0.5 }
        clockwise { false }
        qualityMax { 50 }
        tsvKeys {
            idKey { "id" }
            stepSizeKey { "step size" }
            distanceKey { "distance" }
            qualityKey { "quality" }
        }
    }

    // or this, but isn't this just named parameters without commas? it's also what gradle is doing tho
    val dslScanner2 = scanner {
        id = "ScannerId"
        incremental = true
        stepAngle = 0.5
        clockwise = false
        qualityMax = 50
        tsvKeys {
            idKey { "id" }
            stepSizeKey { "step size" }
            distanceKey { "distance" }
            qualityKey { "quality" }
        }
    }

    // We can also do these shenanigans
    val dslScanner3 = notBuiltScanner {
        id { "ScannerId" }
    } withIncremental true withStepAngle {
        0.5
    } withClockwise {
        false
    } withQualityMax {
        50
    } withTsvKeys {
        idKey { "id" }
        stepSizeKey { "step size" }
        distanceKey { "distance" }
        qualityKey { "quality" }
    }

    // comparing all of this to named parameters... not much difference
    val namedScanner = Scanner(
        id = "ScannerId",
        incremental = true,
        stepAngle = 0.5,
        clockwise = false,
        qualityMax = 50,
        tsvKeys = TsvKeys(
            idKey = "id",
            stepSizeKey = "step size",
            distanceKey = "distance",
            qualityKey = "quality"
        )
    )

    assert(dslScanner == dslScanner2)
    assert(dslScanner2 == dslScanner3)
    assert(dslScanner == namedScanner)
}
