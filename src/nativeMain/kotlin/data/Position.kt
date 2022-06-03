package data

import sdl.SDL_WINDOWPOS_CENTERED

value class Position private constructor(private val packed: ULong) {
    val x get() = (packed shr 32).toInt()
    val y get() = packed.toInt()

    constructor(x: Int, y: Int) : this(
        (x.toULong() shl 32) + y.toUInt()
    )

    inline operator fun component1() = x
    inline operator fun component2() = y

    companion object {
        val CENTERED: Position = Position(SDL_WINDOWPOS_CENTERED.toInt(), SDL_WINDOWPOS_CENTERED.toInt())
    }
}