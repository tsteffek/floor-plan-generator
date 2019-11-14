package model

import mu.KotlinLogging
import kotlin.math.sqrt

private val logger by lazy { KotlinLogging.logger {} }

data class Point(val x: Double, val y: Double, val quality: Int) {
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
}
