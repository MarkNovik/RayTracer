package util

import kotlinx.cinterop.toKString
import sdl.SDL_GetError

inline fun sdlError(message: String): Nothing = error("$message: ${SDL_GetError()?.toKString()}")