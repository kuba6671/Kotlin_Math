import kotlin.math.*

class TrapezeMethod(var a: Double, var b: Double, var n: Int){
    private fun f(x: Double): Double {
        return sqrt(1+ cos(x).pow(2.0))
   }
    fun trapeze(): Double {
        var x: Double
        val h = (b - a) / n
        var sum = (f(a) + f(b)) / 2
        for (i in 1 until n) {
            x = a + h * i
            sum += f(x)
        }
        return sum * h
    }
}
fun main(){
    val t1 = TrapezeMethod(0.0,Math.PI,10)
    println("a=0.0, b=PI, n=10")
    //println("Result: "+String.format("%.5f",t1.trapeze()))
    println("Result: "+t1.trapeze())
    println()

    val t2 = TrapezeMethod(0.0,Math.PI,100)
    println("a=0.0, b=PI, n=100")
    println("Result: "+t2.trapeze())
    println()

    val t3 = TrapezeMethod(0.0,Math.PI,1000)
    println("a=0.0, b=PI, n=1000")
    println("Result: "+t3.trapeze())
}


