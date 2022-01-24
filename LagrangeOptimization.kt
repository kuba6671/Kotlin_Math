import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sin

class LagrangeOptimization {
    private fun f(x: Double): Double {
        return sin(x)
    }

    fun Lagrange(
        startInterval: Double,
        midPointInterval: Double,
        endInterval: Double,
        epsilon: Double,
        y: Double,
        maxIteration: Int
    ): Double {
        var i = 0
        val a = DoubleArray(maxIteration)
        val b = DoubleArray(maxIteration)
        val c = DoubleArray(maxIteration)
        val d = DoubleArray(maxIteration)
        a[0] = startInterval
        b[0] = endInterval
        c[0] = midPointInterval
        do {
            d[i] = 0.5 * ((f(a[i]) * (c[i].pow(2.0) - b[i].pow(2.0)) +
                    f(c[i]) * (b[i].pow(2.0) - a[i].pow(2.0)) +
                    f(b[i]) * (a[i].pow(2.0) - c[i].pow(2.0)))
                    / (f(a[i]) * (c[i] - b[i]) + f(c[i]) * (b[i] - a[i]) + f(b[i]) * (a[i] - c[i])))

            if (a[i] < d[i] && d[i] < c[i]) {
                if (f(d[i]) < f(c[i])) {
                    a[i + 1] = a[i]
                    c[i + 1] = d[i]
                    b[i + 1] = c[i]
                } else {
                    a[i + 1] = d[i]
                    c[i + 1] = c[i]
                    b[i + 1] = b[i]
                }
            } else {
                if (c[i] < d[i] && d[i] < b[i]) {
                    if (f(d[i]) < f(c[i])) {
                        a[i + 1] = c[i]
                        c[i + 1] = d[i]
                        b[i + 1] = b[i]
                    } else {
                        a[i + 1] = a[i]
                        c[i + 1] = c[i]
                        b[i + 1] = d[i]
                    }
                }
            }
            i++
            d[i] = 0.5 * ((f(a[i]) * (c[i].pow(2.0) - b[i].pow(2.0)) +
                    f(c[i]) * (b[i].pow(2.0) - a[i].pow(2.0)) +
                    f(b[i]) * (a[i].pow(2.0) - c[i].pow(2.0)))
                    / (f(a[i]) * (c[i] - b[i]) + f(c[i]) * (b[i] - a[i]) + f(b[i]) * (a[i] - c[i])))

            if (i > maxIteration) {
                return d[i]
            }
        } while (b[i] - a[i] > epsilon || abs(d[i] - d[i - 1]) >= y)
        return d[i - 1]
    }
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            val l1 = LagrangeOptimization()
            println("F(x)=sin(x)")
            println("[a,b]=[-3,0.5]")
            System.out.printf("Minimum= %.4f", l1.Lagrange(-3.0, -1.25, 0.5, 0.01, 0.0001, 100))
        }
    }
}