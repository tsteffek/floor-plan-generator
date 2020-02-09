package de.tsteffek.model.geometry

/**
 * A geometric object.
 */
interface GeometricObject {
    /** Computes the distance to another [GeometricObject]. */
    fun distanceTo(other: GeometricObject): Double
    /** Returns a [String] representation in form of tab-separated values.  */
    fun toTSVString(): String
}
