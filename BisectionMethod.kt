import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sqrt

class BisectionMethod(private var a: Double, private var b: Double, private var epsilon: Double) {
    private fun f(x: Double): Double {
        return sqrt(x) - cos(0.374 + x)
    }

    fun Bisection(): Double {
        var x1 = a
        var x2 = b
        var x: Double
        var y: Double
        var y1: Double
        var i = 0
        do {
            i++
            x = (x1 + x2) / 2.0
            y = f(x)
            y1 = f(x1)
            if (y * y1 > 0) {
                x1 = x
            } else {
                x2 = x
            }
        } while (abs(f(x)) > epsilon)

        println("Number of iterations = $i")
        System.out.printf("y in %d iterations = %.5f\n", i, y)
        System.out.printf("x in %d iterations = %.5f\n", i, x)
        return x
    }
}
fun main() {
    val b1 = BisectionMethod(0.0, 5.0, 0.01)
    b1.Bisection()
}
