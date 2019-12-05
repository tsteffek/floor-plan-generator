package model.geometry

interface GeometricObject<T> {
    fun distanceTo(other: T): Double
    fun toTSVString(): String
}
