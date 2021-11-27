import java.util.stream.DoubleStream

class Taylor internal constructor(var x: DoubleArray, var y: DoubleArray) {
    fun TaylorDerivative() {
        val h = x[1] - x[0]
        val x0 = x[x.size - 1]
        val dfx = Array(5) {
            arrayOfNulls<Double>(6) }
        for (i in x.indices) {
            for (j in 0 until x.size + 1) dfx[i][j] = 0.0
        }
        for (i in x.indices) {
            dfx[i][0] = x[i]
        }
        for (i in x.indices) {
            dfx[i][1] = y[i]
        }
        for (i in 1 until x.size) {
            for (j in 2 until x.size + 1) {
                dfx[i][j] = dfx[i][j - 1]?.minus(dfx[i - 1][j - 1]!!)
            }
        }
         /*for(i in 1 until x.size) {
            for (j in 1 until x.size+1 )
                System.out.printf("%.5f ", dfx[i][j]);
            System.out.println();
        }*/
        var result = 0.0
        var denominator = 1.0
        for (j in 2 until x.size + 1) {
            result += dfx[4][j]!! * (1.0 / denominator)
            denominator += 1.0
        }
        result *= (x0 / h)
        System.out.printf("Result: %.4f ", result)
    }
}
        fun main(args: Array<String>) {
            val x = doubleArrayOf(0.6, 0.7, 0.8, 0.9, 1.0)
            val y = doubleArrayOf(-0.51083, -0.35667, -0.22314, -0.10536, 0.0)
            val t1 = Taylor(x, y)
            t1.TaylorDerivative()
        }

