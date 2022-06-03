package ray_tracing

import math.Vector3

class Ray(
    val point1: Vector3 = Vector3(),
    val point2: Vector3 = Vector3()
) {
    val ab get() = point2 - point1
}