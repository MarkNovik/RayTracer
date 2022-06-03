package data

import sdl.SDL_BIG_ENDIAN
import sdl.SDL_BYTEORDER

data class Color(
    val red: Double,
    val green: Double,
    val blue: Double,
    val alpha: Double = 255.0
) {
    val hex: UInt = kotlin.run {
        val r = (red).toUInt()
        val g = (green).toUInt()
        val b = (blue).toUInt()
        val a = (alpha).toUInt()

        if (SDL_BYTEORDER == SDL_BIG_ENDIAN) {
            (r shl 24) + (g shl 16) + (b shl 8) + a
        } else {
            (a shl 24) + (b shl 16) + (g shl 8) + r
        }
    }

    constructor(red: Number, green: Number, blue: Number, alpha: Number = 255.0) : this(red.toDouble(), green.toDouble(), blue.toDouble(), alpha.toDouble())

    companion object {
        val White: Color = Color(255.0, 255.0, 255.0)
        val Black: Color = Color(0.0, 0.0, 0.0)
    }
}
