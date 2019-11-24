package model

import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

data class Point(val x: Double, val y: Double, val quality: Int) {
    constructor(rotation: Pair<Double, Double>, quality: Int) : this(
        rotation, 1.0, quality
    )

    constructor(rotation: Pair<Double, Double>, length: Double, quality: Int) : this(
        rotation.first * length,
        rotation.second * length,
        quality
    )

    fun getLength(): Double = sqrt(x * x + y * y)

    fun normalizedVec(): Pair<Double, Double> {
        val length = getLength()
        return Pair(x / length, y / length)
    }

    fun toTSVString() = "$x\t$y"

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
