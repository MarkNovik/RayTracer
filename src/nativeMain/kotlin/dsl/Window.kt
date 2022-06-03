package dsl

import cnames.structs.SDL_Window
import data.Position
import data.Size
import kotlinx.cinterop.*
import sdl.*
import util.sdlError

value class Window(val ptr: CPointer<SDL_Window>) {

    inline val size: Size
        get() = memScoped {
            val width = alloc<IntVar>()
            val height = alloc<IntVar>()
            SDL_GetWindowSize(ptr, width.ptr, height.ptr)
            Size(width.value, height.value)
        }

    inline val position: Position
        get() = memScoped {
            val x = alloc<IntVar>()
            val y = alloc<IntVar>()
            SDL_GetWindowPosition(ptr, x.ptr, y.ptr)
            Position(x.value, y.value)
        }

    inline fun renderLoop(
        block: RenderLoop.(canvas: Canvas) -> Unit
    ) {
        val renderer =
            SDL_CreateRenderer(ptr, -1, SDL_RENDERER_ACCELERATED or SDL_RENDERER_PRESENTVSYNC)
                ?: sdlError("SDL RENDERER CREATING ERROR")
        val arena = Arena()
        val loop = RenderLoop()
        val event = arena.alloc<SDL_Event>()

        while (loop.isRunning) {
            while (SDL_PollEvent(event.ptr) != 0) {
                loop.checkEvent(event)
            }
            loop.block(Canvas(renderer))
            SDL_RenderPresent(renderer)
        }

        arena.clear()
        SDL_DestroyRenderer(renderer)
    }
}

fun window(
    title: String,
    position: Position,
    size: Size,
    block: Window.() -> Unit
) {
    if (SDL_Init(SDL_INIT_EVERYTHING) < 0) sdlError("SDL INIT ERROR")
    val window =
        SDL_CreateWindow(title, position.x, position.y, size.width, size.height, 0)
            ?: sdlError("SDL WINDOW CREATING ERROR")

    block(Window(window))

    SDL_DestroyWindow(window)
    SDL_Quit()
}

