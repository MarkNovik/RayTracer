package ray_tracing

import data.Color
import data.Image
import math.Vector3
import objects.Sphere
import kotlin.math.max
import kotlin.math.min

class Scene {

    private val camera = Camera(
        position = Vector3(0.0, -10.0, 0.0),
        lookAt = Vector3(0.0, 0.0, 0.0),
        upVector = Vector3(0.0, 0.0, 1.0),
        width = 0.25,
        aspectRatio = 16.0 / 9.0
    )

    private val sphere = Sphere(
        color = Color(185, 155, 248)
    )

    fun renderImage(output: Image): Boolean {
        val (w, h) = output.size

        val xFact = 1.0 / (w / 2.0)
        val yFact = 1.0 / (h / 2.0)
        var minDist = 1e6
        var maxDist = 0.0
        repeat(w) { x ->
            repeat(h) { y ->
                val xNorm = (x * xFact) - 1.0
                val yNorm = (y * yFact) - 1.0
                val ray = camera.generateRay(xNorm, yNorm)
                val intersection = sphere.intersectWith(ray)
                if (intersection != null) {
                    val (intPoint, _, color) = intersection
                    val (r, g, b) = color
                    val dist = (intPoint - ray.point1).length
                    maxDist = max(dist, maxDist)
                    minDist = min(dist, minDist)
                    output[x, y] = Color(r - ((dist - 9) / 0.94605) * 255.0, g - ((dist - 9) / 0.94605) * 255.0, b - ((dist - 9) / 0.94605) * 255.0)
                }
            }
        }

        println("minDist = $minDist")
        println("maxDist = $maxDist")

        return true
    }

    fun colorAxis(output: Image) {
        val (w, h) = output.size
        repeat(w) { x ->
            repeat(h) { y ->
                val red = (x.toDouble() / w * 255.0)
                val green = (y.toDouble() / h * 255.0)
                output[x, y] = Color(red, green, 0.0)
            }
        }
    }
}
