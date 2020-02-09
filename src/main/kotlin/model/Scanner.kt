package model

/**
 * Data class to contain all the relevant information about a 2d radial lidar scanner.
 * @property id an id
 * @property incremental whether the scanner outputs incremental data, i.e. the data can't be seen as a total step size from an arbitrary starting line, but an incrementation of the last step.
 * @property stepAngle the angle the scanner rotates per step
 * @property clockwise whether the scanner rotates clockwise or counter-clockwise
 * @property qualityMax the max value at which the quality isn't too bad
 * @property idKey the key for the id-field in a tsv
 * @property stepSizeKey the key for the stepSize-field in a tsv
 * @property distanceKey the key for the distance-field in a tsv
 * @property qualityKey the key for the quality-field in a tsv
 */
data class Scanner(
    val id:String,
    val incremental: Boolean,
    val stepAngle: Double,
    val clockwise: Boolean,
    val qualityMax: Int,
    val idKey: String = "id",
    val stepSizeKey: String = "step size",
    val distanceKey: String = "distance",
    val qualityKey: String = "quality"
)
