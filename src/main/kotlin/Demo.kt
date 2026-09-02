// ============================================================================
// Démonstration du cours (séance 2) — séquentiel vs concurrent, chronométré.
// À projeter : le main exécute les deux versions à la suite.
// ============================================================================
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    println("--- Version séquentielle ---")
    val t1 = measureTimeMillis {
        val a = async { delay(1000); "produits" }.await()
        val b = async { delay(1500); "collectes" }.await()
        println("$a + $b")
    }
    println("Durée : $t1 ms\n")

    println("--- Version concurrente ---")
    val t2 = measureTimeMillis {
        val a = async { delay(1000); "produits" }
        val b = async { delay(1500); "collectes" }
        println(a.await() + " + " + b.await())
    }
    println("Durée : $t2 ms")
}
