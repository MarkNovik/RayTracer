package dsl

import cnames.structs.SDL_Renderer
import data.Color
import data.Image
import data.Position
import kotlinx.cinterop.CPointer
import sdl.SDL_RenderDrawPoint
import sdl.SDL_SetRenderDrawColor

value class Canvas(val ptr: CPointer<SDL_Renderer>) {
    fun drawImage(image: Image, topLeft: Position = Position(0, 0)) {
        val (w, h) = image.size
        repeat(w) { x ->
            repeat(h) { y ->
                val (r, g, b, a) = image[x, y]
                SDL_SetRenderDrawColor(ptr, (r).toUByte(), (g).toUByte(), (b).toUByte(), (a).toUByte())
                SDL_RenderDrawPoint(ptr, x + topLeft.x, y + topLeft.y)
            }
        }
    }

    fun clear(color: Color) {
        val (r, g, b) = color
        SDL_SetRenderDrawColor(ptr, (r).toUByte(), (g).toUByte(), (b).toUByte(), 255)
    }
}

inline fun Double.toUByte() = toUInt().toUByte()