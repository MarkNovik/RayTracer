package objects

import data.Color
import ray_tracing.Ray
import data.Intersection
import ray_tracing.Transformation

sealed class Object(
    val transformation: Transformation,
    val color: Color
) {
    abstract fun intersectWith(castRay: Ray): Intersection?
}