package model.geometry

const val PRECISION = 1e-8

interface GeometricObject<T> {
    fun distanceTo(other: T): Double
    fun toTSVString(): String
}
