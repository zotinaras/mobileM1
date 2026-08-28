plugins {
    kotlin("jvm") version "2.0.20"
}
kotlin { jvmToolchain(21) }
repositories { mavenCentral() }

// Aucune dépendance : Kotlin pur.
// Pour lancer : ouvrir src/main/kotlin/Collectes.kt dans Android Studio
// et cliquer sur la flèche verte à côté de fun main().
