import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.pow

class Differential {
    private fun dydx(x: Double, y: Double): Double {
        return x.pow(2.0) + cos(y / PI)
    }
    fun rungeKutta(x0: Double, y0: Double, xn: Double, h: Double): Double {
        var x0 = x0
        val n = ((xn - x0) / h).toInt()
        var k1: Double
        var k2: Double
        var k3: Double
        var k4: Double
        var y = y0
        for (i in 0 until n) {
            k1 = h * dydx(x0, y)
            k2 = h * dydx(x0 + 0.5 * h, y + 0.5 * k1)
            k3 = h * dydx(x0 + 0.5 * h, y + 0.5 * k2)
            k4 = h * dydx(x0 + h, y + k3)
            y += 1.0 / 6.0 * (k1 + 2.0 * k2 + 2.0 * k3 + k4)
            x0 += h
        }
        return y
    }
}
fun main() {
    val d1 = Differential()
    val x0 = 1.7
    val y0 = 5.3
    val xn = 2.7
    val h = 0.1
    println("The value of y at x is : " + d1.rungeKutta(x0, y0, xn, h))
}