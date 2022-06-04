package math

import kotlin.math.*

const val EPSILON = 1.2246467991473532e-16//1e-21

infix fun Double.isCloseEnoughTo(other: Double): Boolean =
    abs(this - other) < EPSILON
