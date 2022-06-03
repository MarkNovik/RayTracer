package ray_tracing.light

import data.Intersection
import data.Illumination
import objects.Object
import data.Color
import math.Vector3
import kotlin.math.PI

class PointLight(
    private val location: Vector3,
    private val color: Color,
    private val intencity: Double = 1.0
) : Light {
    override fun computeIllumination(
        intersection: Intersection,
        objects: List<Object>,
        currentObject: Object
    ): Illumination? {
        val (point, normal) = intersection
        val lightDir = (location - point).normalized
        //val startPoint = point
        val angle = normal.angleWith(lightDir)
        if (angle > PI / 2.0){
            return null
        }
        return Illumination(
            this.color,
            this.intencity * (1.0 - angle / (PI / 2))
        )
    }
}
