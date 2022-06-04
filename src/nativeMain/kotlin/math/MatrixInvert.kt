package math

fun Matrix.inversed(): Matrix {
    var A2323 = this[2,2] * this[3,3] - this[3,2] * this[2,3]
    var A1323 = this[1,2] * this[3,3] - this[3,2] * this[1,3]
    var A1223 = this[1,2] * this[2,3] - this[2,2] * this[1,3]
    var A0323 = this[0,2] * this[3,3] - this[3,2] * this[0,3]
    var A0223 = this[0,2] * this[2,3] - this[2,2] * this[0,3]
    var A0123 = this[0,2] * this[1,3] - this[1,2] * this[0,3]
    var A2313 = this[2,1] * this[3,3] - this[3,1] * this[2,3]
    var A1313 = this[1,1] * this[3,3] - this[3,1] * this[1,3]
    var A1213 = this[1,1] * this[2,3] - this[2,1] * this[1,3]
    var A2312 = this[2,1] * this[3,2] - this[3,1] * this[2,2]
    var A1312 = this[1,1] * this[3,2] - this[3,1] * this[1,2]
    var A1212 = this[1,1] * this[2,2] - this[2,1] * this[1,2]
    var A0313 = this[0,1] * this[3,3] - this[3,1] * this[0,3]
    var A0213 = this[0,1] * this[2,3] - this[2,1] * this[0,3]
    var A0312 = this[0,1] * this[3,2] - this[3,1] * this[0,2]
    var A0212 = this[0,1] * this[2,2] - this[2,1] * this[0,2]
    var A0113 = this[0,1] * this[1,3] - this[1,1] * this[0,3]
    var A0112 = this[0,1] * this[1,2] - this[1,1] * this[0,2]

    var det = 
        this[0,0] * (this[1,1] * A2323 - this[2,1] * A1323 + this[3,1] * A1223) -
        this[1,0] * (this[0,1] * A2323 - this[2,1] * A0323 + this[3,1] * A0223) +
        this[2,0] * (this[0,1] * A1323 - this[1,1] * A0323 + this[3,1] * A0123) -
        this[3,0] * (this[0,1] * A1223 - this[1,1] * A0223 + this[2,1] * A0123) 
    det = 1.0 / det

    return Matrix { x, y ->
        when(y to x) {
            0 to 0 -> det * ( (this[1,1] * A2323 - this[2,1] * A1323 + this[3,1] * A1223))
            0 to 1 -> det * (-(this[1,0] * A2323 - this[2,0] * A1323 + this[3,0] * A1223))
            0 to 2 -> det * ( (this[1,0] * A2313 - this[2,0] * A1313 + this[3,0] * A1213))
            0 to 3 -> det * (-(this[1,0] * A2312 - this[2,0] * A1312 + this[3,0] * A1212))
            1 to 0 -> det * (-(this[0,1] * A2323 - this[2,1] * A0323 + this[3,1] * A0223))
            1 to 1 -> det * ( (this[0,0] * A2323 - this[2,0] * A0323 + this[3,0] * A0223))
            1 to 2 -> det * (-(this[0,0] * A2313 - this[2,0] * A0313 + this[3,0] * A0213))
            1 to 3 -> det * ( (this[0,0] * A2312 - this[2,0] * A0312 + this[3,0] * A0212))
            2 to 0 -> det * ( (this[0,1] * A1323 - this[1,1] * A0323 + this[3,1] * A0123))
            2 to 1 -> det * (-(this[0,0] * A1323 - this[1,0] * A0323 + this[3,0] * A0123))
            2 to 2 -> det * ( (this[0,0] * A1313 - this[1,0] * A0313 + this[3,0] * A0113))
            2 to 3 -> det * (-(this[0,0] * A1312 - this[1,0] * A0312 + this[3,0] * A0112))
            3 to 0 -> det * (-(this[0,1] * A1223 - this[1,1] * A0223 + this[2,1] * A0123))
            3 to 1 -> det * ( (this[0,0] * A1223 - this[1,0] * A0223 + this[2,0] * A0123))
            3 to 2 -> det * (-(this[0,0] * A1213 - this[1,0] * A0213 + this[2,0] * A0113))
            3 to 3 -> det * ( (this[0,0] * A1212 - this[1,0] * A0212 + this[2,0] * A0112))
            else -> TODO("Unrrachable matrix coordinates")
        }
    }
}