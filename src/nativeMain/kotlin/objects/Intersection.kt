package objects

import data.Color
import math.Vector3

data class Intersection(
    val point: Vector3,
    val normal: Vector3,
    val color: Color
)