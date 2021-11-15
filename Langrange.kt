import java.util.*

object Lagrange {
    private fun rate_value(rate_array: List<Double?>, v: DoubleArray): MutableList<*> {
        val m = rate_array.size
        val n = v.size
        val rate_array_tmp: MutableList<Double> = ArrayList<Double>()
        for (k in 1 until m + n) {
            var tmp = 0.0
            for (j in 0 until k) {
                if (j > m - 1 || k - j - 1 > n - 1) continue
                tmp += rate_array[j]!! * v[k - j - 1]
            }
            rate_array_tmp.add(tmp)
        }
        return rate_array_tmp
    }
    fun LagrangeInterpolation(x: Array<Double>, y: Array<Double>): Array<Double?> {
        val size = x.size
        val result = arrayOfNulls<Double>(size)
        for (i in 0 until size) {
            result[i] = 0.0
        }
        for (i in x.indices) {
            var rate_array: MutableList<Double> = ArrayList<Double>()
            rate_array.add(1.0)
            for (j in x.indices) {
                if (j != i) {
                    val tmp = doubleArrayOf(1.0, -x[j])
                    rate_array = rate_value(rate_array, tmp) as MutableList<Double>
                    for (k in rate_array.indices) {
                        rate_array[k] = rate_array[k]!! / (x[i] - x[j])
                    }
                }
            }
            for (k in rate_array.indices) {
                result[k] = result[k]?.plus(y[i] * rate_array[k]!!)
            }
        }
        return result
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val x = arrayOf(0.81,0.85,0.91,0.99,1.05,1.1)
        val y = arrayOf(9.75421,7.31659,5.3917,4.051069,3.439510,3.06522)
        println("x="+ x.contentToString())
        println("y="+ y.contentToString())
        println("Result:")
        println(LagrangeInterpolation(x, y).contentToString())
        println()
        val x2 = arrayOf(0.81,0.85,0.91,0.99,1.05,1.1,1.15,1.21)
        val y2 = arrayOf(9.75421,7.31659,5.3917,4.051069,3.439510,3.06522, 2.73219, 2.21341)
        println("x2="+ x2.contentToString())
        println("y2="+ y2.contentToString())
        println("Result:")
        println(LagrangeInterpolation(x2, y2).contentToString())
    }
}