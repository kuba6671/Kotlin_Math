class Taylor internal constructor(private var x: DoubleArray,private var y: DoubleArray) {
    fun taylorDerivative() {
        val h = x[1] - x[0]
        val x0 = x[x.size - 1]
        val dfx = Array(x.size) {
            arrayOfNulls<Double>(x.size+1) }
        for (i in x.indices) {
            for (j in 0 until x.size + 1) dfx[i][j] = 0.0
        }
        for (i in x.indices) {
            dfx[i][0] = x[i]
            dfx[i][1] = y[i]
        }
        for (i in 1 until x.size) {
            for (j in 2 until x.size + 1) {
                dfx[i][j] = dfx[i][j - 1]?.minus(dfx[i - 1][j - 1]!!)
            }
        }
        var result = 0.0
        var denominator = 1.0
        for (j in 2 until x.size + 1) {
            result += dfx[4][j]!! * (1.0 / denominator)
            denominator += 1.0
        }
        result *= (x0 / h)
        System.out.printf("Result: %.4f\n", result)
    }
}
        fun main() {
            val x = doubleArrayOf(1.3,1.6,1.9,2.2,2.5,2.8,3.1)
            val y = doubleArrayOf(0.970547,1.01197,1.063698,1.107149,1.144169,1.176005,1.203622)
            val t1 = Taylor(x, y)
            println("x="+x.contentToString())
            println("y="+y.contentToString())
            t1.taylorDerivative()

            println()
            val x2 = doubleArrayOf(1.3,1.6,1.9,2.2,2.5,2.8,3.1,3.4,3.7)
            val y2 = doubleArrayOf(0.970547,1.01197,1.063698,1.107149,1.144169,1.176005,1.203622, 1.233143, 1.270312)
            val t2 = Taylor(x2, y2)
            println("x2="+x2.contentToString())
            println("y2="+y2.contentToString())
            t2.taylorDerivative()
        }

