import java.text.DecimalFormat
import java.util.*
import kotlin.math.abs

class Gaussian {
    fun GaussianElimination(A: Array<DoubleArray>, B: DoubleArray) {
        val N = B.size
        for (s in 0 until N) {
            var max: Int = s
            for (i in s + 1 until N) if (abs(A[i][s]) > abs(A[max][s])) max = i
            val temp = A[s]
            A[s] = A[max]
            A[max] = temp
            val t = B[s]
            B[s] = B[max]
            B[max] = t
            for (i in s + 1 until N) {
                val factor = A[i][s] / A[s][s]
                B[i] -= factor * B[s]
                for (j in s until N) A[i][j] -= factor * A[s][j]
            }
        }
        printMatrix(A, B)
        val solution = DoubleArray(N)
        for (i in N - 1 downTo 0) {
            var sum = 0.0
            for (j in i + 1 until N) sum += A[i][j] * solution[j]
            solution[i] = (B[i] - sum) / A[i][i]
        }
        val df = DecimalFormat("0.00")
        println("Result: ")
        print("[ ")
        Arrays.stream(solution).forEach { e: Double -> print(df.format(e) + " ") }
        print("]\n")
    }

    fun printMatrix(A: Array<DoubleArray>, B: DoubleArray) {
        val N = B.size
        println("Result matrix: ")
        for (i in 0 until N) {
            print("[ ")
            for (j in 0 until N) System.out.printf("%.2f ", A[i][j])
            System.out.printf("| %.2f", B[i])
            println("]")
        }
    }

    fun printMatrix(A: Array<DoubleArray>) {
        val rows = A.size
        val columns: Int = A[0].size
        for(row in A){
            println(row.contentToString())
        }
    }
}

fun main() {
    val g1 = Gaussian()
    val A = arrayOf(
        doubleArrayOf(6.34, 11.75, 10.0),
        doubleArrayOf(7.42, -19.03, 11.75),
        doubleArrayOf(5.57, 7.48, 6.36)
    )
    val B = doubleArrayOf(-41.4, -49.49, -27.67)
    println("A=")
    g1.printMatrix(A)
    println(""" B= ${B.contentToString()} """.trimIndent())
    g1.GaussianElimination(A, B)
}

