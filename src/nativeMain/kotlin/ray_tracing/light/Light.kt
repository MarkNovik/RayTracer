package ray_tracing.light

import data.Intersection
import data.Illumination
import objects.Object
import data.Color

sealed interface Light {
    fun computeIllumination(
        intersection: Intersection,
        objects: List<Object>,
        currentObject: Object
    ): Illumination?
}
