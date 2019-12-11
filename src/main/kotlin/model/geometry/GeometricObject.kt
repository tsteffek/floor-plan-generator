package model.geometry

const val PRECISION = 1e-8

interface GeometricObject {
    fun distanceTo(other: GeometricObject): Double
    fun toTSVString(): String
}
