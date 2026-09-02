plugins {
    kotlin("jvm") version "2.0.20"
}

repositories { mavenCentral() }

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
}

// Pour lancer un programme : ouvrir le fichier voulu dans Android Studio
// et cliquer sur la flèche verte à côté de son main().
