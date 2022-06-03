package data


class Image(val size: Size) {
    private val map: Array<Array<Color>> = Array(size.width) { Array(size.height) { Color(0.0, 0.0, 0.0) } }

    operator fun set(x: Int, y: Int, color: Color) {
        map[x][y] = color
    }

    operator fun get(x: Int, y: Int): Color = map[x][y]
}