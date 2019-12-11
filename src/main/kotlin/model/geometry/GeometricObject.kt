package model.geometry

interface GeometricObject {
    fun distanceTo(other: GeometricObject): Double
    fun toTSVString(): String
}
