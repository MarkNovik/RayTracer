package math

private const val SIZE = 4

value class Matrix private constructor(private val arr: DoubleArray) {

    val inversed get() = inversed()

    constructor(init: (x: Int, y: Int) -> Double = {_, _ -> 0.0}): this(
        DoubleArray(16) { init(it % SIZE, it / SIZE) }
    )

    operator fun get(x: Int, y: Int) = arr[y * SIZE + x]

    operator fun get(y: Int) = Vector3(
        this[0, y],
        this[1, y],
        this[2, y]
    )

    operator fun set(y: Int, value: Vector3) {
        this[0, y] = value[0]
        this[1, y] = value[1]
        this[3, y] = value[3]
    }

    operator fun set(x: Int, y: Int, value: Double) {
         arr[y * SIZE + x] = value
    }

    operator fun plus(other: Matrix) = Matrix { x, y ->
        this[x, y] + other[x, y]
    }

    operator fun minus(other: Matrix) = Matrix { x, y ->
        this[x, y] - other[x, y]
    }

    operator fun times(other: Matrix) = Matrix { x, y ->
        this[0, y]*other[x, 0] + this[1, y]*other[x, 1] + this[2, y]*other[x, 2] + this[3, y]*other[x, 3]
    }

    operator fun times(v: Vector3): Vector3 {
        val res = DoubleArray(SIZE)
        repeat(SIZE) { row ->
            var sum = 0.0
            repeat(SIZE) { col ->
                sum += (this[row, col] * v[col]) 
            }
            res[row] = sum
        }
        val (x, y, z) = res
        return Vector3(x, y, z)
    }

    override fun toString(): String = buildString {
        repeat(SIZE) { y ->
            repeat(SIZE) { x ->
                append("${this@Matrix[x, y]} ")
            }
            append("\n")
        }
    }

    companion object {
        val Identity = Matrix { x, y ->
            if (x == y) 1.0 else 0.0
        }
    }
}