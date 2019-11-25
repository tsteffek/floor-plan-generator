package model

import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

data class Point(val x: Double, val y: Double, val quality: Int) {

    fun getLength(): Double = sqrt(x * x + y * y)

    fun normalizedDirection(): Pair<Double, Double> {
        val length = getLength()
        return Pair(x / length, y / length)
    }

    fun rotateBy(angle: Double): Point {
        val rad = Math.toRadians(angle)
        val x2 = cos(rad) * x - sin(rad) * y
        val y2 = sin(rad) * x + cos(rad) * y
        return Point(x2, y2, quality)
    }

    fun toTSVString() = "$x\t$y\t$quality"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Point

        if (quality != other.quality) return false
        if (other.x - 1e-8 > x || x > other.x + 1e-8) return false
        if (other.y - 1e-8 > y || y > other.y + 1e-8) return false

        return true
    }

    companion object {
        fun fromPolarCoordinates(distance: Double, angle: Double, quality: Int): Point {
            val rad = Math.toRadians(angle)
            return Point(distance * cos(rad), distance * sin(rad), quality)
        }
    }
}
