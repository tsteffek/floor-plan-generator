package model

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
