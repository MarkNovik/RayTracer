package ray_tracing

import ray_tracing.Ray
import math.Vector3
import math.Matrix
import kotlin.math.sin
import kotlin.math.cos

class Transformation private constructor(
    val forward: Matrix = Matrix.Identity,
) {
    val backward = forward.inversed

    fun apply(inputRay: Ray, target: ApplyTarget) = Ray(
        apply(inputRay.point1, target),
        apply(inputRay.point2, target)
    )

    fun apply(vector: Vector3, target: ApplyTarget): Vector3 {
        val targetMatrix = if (target == ApplyTarget.FORWARD) forward else backward
        return targetMatrix * vector
    }

    operator fun times(other: Transformation): Transformation =
        Transformation(this.forward * other.forward)
    

    override fun toString(): String = buildString {
        append("Forward: \n")
        append(forward)
        append("Backward: \n")
        append(backward)
    }

    companion object {
        fun by(
            translation: Vector3,
            rotation: Vector3,
            scale: Vector3
        ): Transformation {
            val translationMatrix = Matrix.Identity
            val rotationMatrixX = Matrix.Identity
            val rotationMatrixY = Matrix.Identity
            val rotationMatrixZ = Matrix.Identity
            val scaleMatrix = Matrix.Identity

            translationMatrix[3, 0] = translation.x
            translationMatrix[3, 1] = translation.y
            translationMatrix[3, 2] = translation.z

            rotationMatrixZ[0, 0] = cos(rotation.z)
            rotationMatrixZ[1, 0] = -sin(rotation.z)
            rotationMatrixZ[0, 1] = sin(rotation.z)
            rotationMatrixZ[1, 1] = cos(rotation.z)

            rotationMatrixY[0, 0] = cos(rotation.y)
            rotationMatrixY[2, 0] = -sin(rotation.y)
            rotationMatrixY[0, 2] = sin(rotation.y)
            rotationMatrixY[2, 2] = cos(rotation.y)

            rotationMatrixX[0, 0] = cos(rotation.x)
            rotationMatrixX[2, 1] = -sin(rotation.x)
            rotationMatrixX[1, 2] = sin(rotation.x)
            rotationMatrixX[2, 2] = cos(rotation.x)

            scaleMatrix[0, 0] = scale.x
            scaleMatrix[1, 1] = scale.y
            scaleMatrix[2, 2] = scale.z

            val res = translationMatrix *
                      scaleMatrix       *
                      rotationMatrixX   *
                      rotationMatrixY   *
                      rotationMatrixZ

            return Transformation(res)
        }
    }

    enum class ApplyTarget {
        FORWARD,
        BACKWARD
    }
}