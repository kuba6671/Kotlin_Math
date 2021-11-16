import java.util.stream.DoubleStream
import kotlin.math.pow

class Approximation internal constructor(private var x: DoubleArray, private var y: DoubleArray) {
    fun leastSquares() {
        val x0 = DoubleArray(x.size)
        for (i in x.indices) {
            x0[i] = 1.0
        }
        val S0 = DoubleStream.of(*x0).sum()
        val S1 = DoubleStream.of(*x).sum()
        val x2 = DoubleArray(x.size)
        for (i in x2.indices) {
            x2[i] = x[i].pow(2.0)
        }
        val S2 = DoubleStream.of(*x2).sum()
        val T0 = DoubleStream.of(*y).sum()
        val xy = DoubleArray(x.size)
        for (i in x.indices) {
            xy[i] = x[i] * y[i]
        }
        val T1 = DoubleStream.of(*xy).sum()
        val determinant = S0 * S2 - S1 * S1
        if (determinant != 0.0) {
            val a0 = (T0 * S2 - S1 * T1) / determinant
            val a1 = (S0 * T1 - T0 * S1) / determinant
            println("Result:")
            println("a0=$a0")
            println("a1=$a1")
            System.out.printf("Q(x)=%.2f+%.2fx\n", a0, a1)
        }
    }
}
fun main() {
            val x = doubleArrayOf(6.63, 7.15, 2.96, 1.73, 7.44, 3.70, 2.0, 2.63)
            val y = doubleArrayOf(105.2, 85.02, 52.76, 56.86, 72.19, 61.09, 70.44, 51.67)
            println("x=" + x.contentToString())
            println("y=" + y.contentToString())
            val a1 = Approximation(x, y)
            a1.leastSquares()
            println()
            val x2 = doubleArrayOf(6.63, 7.15, 2.96, 1.73, 7.44, 3.70, 2.0, 2.63, 3.31, 3.83)
            val y2 = doubleArrayOf(105.2, 85.02, 52.76, 56.86, 72.19, 61.09, 70.44, 51.67, 45.13, 38.83)
            println("x2=" + x2.contentToString())
            println("y2=" + y2.contentToString())
            val a2 = Approximation(x2, y2)
            a2.leastSquares()
}

