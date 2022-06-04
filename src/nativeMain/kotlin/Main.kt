import data.Color
import data.Image
import data.Position
import data.Size
import dsl.window
import kotlinx.cinterop.toKString
import math.Vector3
import ray_tracing.Camera
import ray_tracing.Scene
import sdl.SDL_GetError
import sdl.SDL_QUIT
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime
import math.Matrix

@OptIn(ExperimentalTime::class)
fun main() = window(
    title = "Ray Tracing",
    position = Position.CENTERED,
    size = Size(1280, 720),
) {
    val (width, height) = size
    val image = Image(Size(width, height))
    val scene = Scene()

    scene.renderImage(image)

    renderLoop { canvas ->
        println(measureTime {
            onEvent(SDL_QUIT) { stop() }
            //canvas.clear(Color.White)
            //scene.renderImage(image)
            canvas.drawImage(image)
        })
    }
}