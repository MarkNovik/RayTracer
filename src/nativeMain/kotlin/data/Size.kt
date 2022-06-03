package data

value class Size private constructor(private val packed: ULong) {
    val width get() = (packed shr 32).toInt()
    val height get() = packed.toInt()

    constructor(width: Int, height: Int) : this(
        (width.toULong() shl 32) + height.toUInt()
    )

    inline operator fun component1() = width
    inline operator fun component2() = height
}