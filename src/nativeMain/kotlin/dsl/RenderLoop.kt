package dsl

import sdl.SDL_Event
import sdl.SDL_EventType

private typealias OnEvent = (event: SDL_Event) -> Unit

class RenderLoop {
    var isRunning: Boolean = true
        private set

    private val eventReactions = mutableMapOf<SDL_EventType, OnEvent>()

    fun stop() {
        isRunning = false
    }

    fun onEvent(type: SDL_EventType, block: OnEvent) {
        eventReactions[type] = block
    }

    fun checkEvent(event: SDL_Event) {
        eventReactions[event.type]?.invoke(event)
    }
}