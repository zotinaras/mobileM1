// ============================================================================
// Mini-TP 2 — Programme 1 : launch
// AVANT d'exécuter : prédisez l'ordre EXACT des affichages et la durée totale.
// (Vos prédictions se soumettent sur Moodle — Test « S2 · Prédictions ».)
// ============================================================================
import kotlinx.coroutines.*

fun main() = runBlocking {
    println("A - début de la synchronisation")
    launch {
        delay(500)
        println("B - collectes reçues")
    }
    println("C - interface prête")
}
