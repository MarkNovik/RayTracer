package ray_tracing

import math.Vector3
import math.times

class Camera(
    val position: Vector3 = Vector3(0.0, -10.0, 0.0),
    val lookAt: Vector3 = Vector3(0.0, 0.0, 0.0),
    val upVector: Vector3 = Vector3(0.0, 0.0, 1.0),
    val length: Double = 1.0,
    val width: Double = 1.0,
    val aspectRatio: Double = 1.0
) {

    val u: Vector3 get() = projectionScreenU
    val v: Vector3 get() = projectionScreenV
    val screenCenter: Vector3 get() = projectionScreenCenter
    private var alignmentVector: Vector3 = Vector3(0.0, 0.0, 0.0)
    private var projectionScreenU: Vector3 = Vector3(0.0, 0.0, 0.0)
    private var projectionScreenV: Vector3 = Vector3(0.0, 0.0, 0.0)
    private var projectionScreenCenter: Vector3 = Vector3(0.0, 0.0, 0.0)

    init {
        updateGeometry()
    }

    fun generateRay(proScreenX: Double, proScreenY: Double): Ray {
        val screenWorldPart1 = (projectionScreenCenter + (projectionScreenU * proScreenX))
        val screenWorldCoordinate = screenWorldPart1 + (projectionScreenV * proScreenY)
        return Ray(position, screenWorldCoordinate)
    }

    private fun updateGeometry() {
        alignmentVector = (lookAt - position).normalized
        projectionScreenU = (alignmentVector cross upVector).normalized
        projectionScreenV = (projectionScreenU cross alignmentVector).normalized

        projectionScreenCenter = position + (length * alignmentVector)

        projectionScreenU *= width
        projectionScreenV *= (width / aspectRatio)
    }
}
