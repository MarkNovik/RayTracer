package objects

import data.Color
import ray_tracing.Ray
import data.Intersection

sealed interface Object {
    val color: Color
    fun intersectWith(castRay: Ray): Intersection?
}