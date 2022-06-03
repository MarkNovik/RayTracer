package math

import kotlin.math.abs

const val EPSILON = 1e-21

infix fun Double.isCloseEnoughTo(other: Double): Boolean =
    abs(this - other) < EPSILON
