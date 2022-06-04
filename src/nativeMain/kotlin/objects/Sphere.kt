package objects

import data.Color
import data.Intersection
import math.Vector3
import ray_tracing.Ray
import ray_tracing.Transformation
import kotlin.math.sqrt
import ray_tracing.Transformation.ApplyTarget


class Sphere(
    transformation: Transformation,
    color: Color
) : Object(transformation, color) {
    override fun intersectWith(
        castRay: Ray
    ): Intersection? {

        val tRay = transformation.apply(castRay, ApplyTarget.BACKWARD)

        val vhat = tRay.ab.normalized
        val b = 2.0 * (tRay.point1 dot vhat)
        val c = (tRay.point1 dot tRay.point1) - 1.0
        val d = (b * b) - 4.0 * c
        if (d > 0.0) {
            val sqrtD = sqrt(d)
            val t1 = (-b + sqrtD) / 2.0
            val t2 = (-b - sqrtD) / 2.0
            if (t1 < 0 || t2 < 0) return null
            val poi =
                if (t1 < t2) tRay.point1 + (vhat * t1)
                else tRay.point1 + (vhat * t2)
            val intPoint = transformation.apply(poi, ApplyTarget.FORWARD)
            val originShift = transformation.apply(Vector3(), ApplyTarget.FORWARD)
            return Intersection(intPoint, (intPoint - originShift).normalized)
        }
        return null
    }
}