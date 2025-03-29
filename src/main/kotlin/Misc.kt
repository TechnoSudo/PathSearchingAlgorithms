package com.technosudo

import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.time.Duration
import kotlin.time.Duration.Companion.microseconds
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.nanoseconds
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit

object Misc {

    fun Collection<Duration>.ave(): Duration {
        return map { it.inWholeNanoseconds }.average().toLong().nanoseconds
    }

    fun Collection<Duration>.std(): Duration {
        if (isEmpty()) return Duration.ZERO
        val mean = map { it.inWholeNanoseconds }.average()
        val variance = map { (it.inWholeNanoseconds - mean).pow(2) }.average()
        return sqrt(variance).toLong().nanoseconds
    }

    fun Duration.significant(): String {
        return when {
            this < 1.microseconds -> {
                val value = toDouble(DurationUnit.NANOSECONDS)
                String.format("%.3g", value) + "ns"
            }
            this < 1.milliseconds -> {
                val value = toDouble(DurationUnit.MICROSECONDS)
                String.format("%.3g", value) + "µs"
            }
            this < 1.seconds -> {
                val value = toDouble(DurationUnit.MILLISECONDS)
                String.format("%.3g", value) + "ms"
            }
            else -> {
                val value = toDouble(DurationUnit.SECONDS)
                String.format("%.3g", value) + "s"
            }
        }
    }
}