package math

import kotlin.math.sqrt
import kotlin.math.acos

data class Vector3( // TODO: Rename to Vector; move to TestVector structure
    val x: Double = 0.0,
    val y: Double = 0.0,
    val z: Double = 0.0,
) {
    val w get() = 1.0
    val length get() = sqrt(x * x + y * y + z * z)
    val normalized get() = this / length

    fun angleWith(v: Vector3): Double {
        return acos((this dot v) / this.length * v.length)
    }

    operator fun get(i: Int): Double = when(i) {
        0 -> x
        1 -> y
        2 -> z
        3 -> w
        else -> throw NoSuchElementException()
    }

    operator fun plus(other: Vector3) = Vector3(
        this.x + other.x,
        this.y + other.y,
        this.z + other.z,
    )

    operator fun minus(other: Vector3) = Vector3(
        this.x - other.x,
        this.y - other.y,
        this.z - other.z,
    )

    operator fun times(scale: Double) = Vector3(
        x * scale,
        y * scale,
        z * scale
    )

    operator fun div(scale: Double) = Vector3(
        x / scale,
        y / scale,
        z / scale
    )

    infix fun cross(other: Vector3) = Vector3(
        this.y * other.z - this.z * other.y,
        this.z * other.x - this.x * other.z,
        this.x * other.y - this.y * other.x
    )

    infix fun dot(other: Vector3): Double = this.x * other.x + this.y * other.y + this.z * other.z

    operator fun component4() = w

    override fun toString(): String = "($x; $y; $z)"
}

operator fun Double.times(vector: Vector3) = Vector3(
    vector.x * this,
    vector.y * this,
    vector.z * this
)

private const val SIZE = 4

value class TestVector private constructor(private val arr: DoubleArray) {
    val x get() = arr[0]
    val y get() = arr[1]
    val z get() = arr[2]
    val w get() = arr[3]

    constructor(init: (Int) -> Double): this(
        DoubleArray(SIZE, init)
    )

    operator fun component1() = x
    operator fun component2() = y
    operator fun component3() = z
    operator fun component4() = w
}