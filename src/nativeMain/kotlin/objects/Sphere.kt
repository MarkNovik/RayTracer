package objects

import data.Color
import data.Intersection
import math.Vector3
import ray_tracing.Ray
import kotlin.math.sqrt

class Sphere(override val color: Color = Color(255.0, 0.0, 0.0)) : Object {
    override fun intersectWith(
        castRay: Ray
    ): Intersection? {
        val vhat = castRay.ab.normalized
        val b = 2.0 * (castRay.point1 dot vhat)
        val c = (castRay.point1 dot castRay.point1) - 1.0
        val d = (b * b) - 4.0 * c
        if (d > 0.0) {
            val sqrtD = sqrt(d)
            val t1 = (-b + sqrtD) / 2.0
            val t2 = (-b - sqrtD) / 2.0
            if (t1 < 0 || t2 < 0) return null
            val intPoint =
                if (t1 < t2) castRay.point1 + (vhat * t1)
                else castRay.point1 + (vhat * t2)
            return Intersection(intPoint, intPoint.normalized)
        }
        return null
    }
}