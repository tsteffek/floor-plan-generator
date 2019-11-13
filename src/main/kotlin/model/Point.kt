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
}
