// ============================================================================
// Mini-TP 2 — Programme 3 : le piège de l'await
// AVANT d'exécuter : prédisez l'ordre EXACT des affichages et la durée totale.
// (Attention : ce programme ressemble au 2... mais regardez bien les await.)
//
// TRANSFORMATION 2 (après vérification) : rendez ce programme CONCURRENT
// en déplaçant les await, ré-exécutez et notez la nouvelle durée
// en commentaire ci-dessous.
// Durée mesurée après transformation : ......... ms
// ============================================================================
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val duree = measureTimeMillis {
        val poidsVanille = async {
            delay(1000)
            4.5
        }.await()
        val poidsCafe = async {
            delay(800)
            6.0
        }.await()
        println("Poids total : " + (poidsVanille + poidsCafe) + " kg")
    }
    println("Durée totale : environ $duree ms")
}
