package model

data class Scanner(
    val id:String,
    val incremental: Boolean,
    val stepAngle: Double,
    val clockwise: Boolean
)
