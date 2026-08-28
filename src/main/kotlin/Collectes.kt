// ============================================================================
// Mini-TP 1 — Kotlin essentiel : « Lire et transformer »
// ITUniversity — Module M1 · Séance 1
// ----------------------------------------------------------------------------
// Ce fichier COMPILE et S'EXÉCUTE tel quel : lancez main() dès l'ouverture.
//
// Règles du mini-TP :
//   1. Phase principale SANS IA (complétion IA de l'IDE désactivée).
//   2. L'opérateur !! est INTERDIT dans tout le fichier.
//   3. Dans la PARTIE C (trous), aucune boucle for/while : uniquement les
//      opérations de collections (map, filter, groupBy, sumOf, ...).
//
// Déroulé :
//   PARTIE A — LIRE     : parcourez tout le fichier ; annotez 3 endroits
//                         (une construction connue de Java, une nouvelle,
//                         une incomprise).
//   PARTIE B — PRÉDIRE  : AVANT d'exécuter, écrivez sur votre feuille la
//                         sortie exacte des 4 appels P1..P4 du main().
//                         Puis exécutez et notez les écarts.
//   PARTIE C — COMPLÉTER: remplacez les TODO des trous n°1 à n°5.
//                         Pour tester, décommentez verifierTrous() dans main().
//   BONUS ★             : pour ceux qui ont terminé.
// ============================================================================

// ----------------------------------------------------------------------------
// LE DOMAINE : une coopérative agricole malgache
// ----------------------------------------------------------------------------

data class Produit(
    val code: String,
    val nom: String,
    val prixKg: Double?,   // null = prix non encore fixé par la coopérative
)

data class Producteur(
    val id: Int,
    val nom: String,
    val village: String,
)

data class Collecte(
    val produit: Produit,
    val producteur: Producteur,
    val poidsKg: Double,
    val date: String,      // format "2026-08-01" (simplifié pour ce TP)
)

// ----------------------------------------------------------------------------
// JEU DE DONNÉES (fourni)
// ----------------------------------------------------------------------------

val vanille = Produit("VAN", "Vanille", 250_000.0)
val cafe    = Produit("CAF", "Café", 12_000.0)
val girofle = Produit("GIR", "Girofle", 38_000.0)
val litchi  = Produit("LIT", "Litchi", null)          // prix non fixé

val producteurs = listOf(
    Producteur(1, "RAKOTO Jean", "Ambodivoara"),
    Producteur(2, "RASOA Marie", "Antsirabe Nord"),
    Producteur(3, "RANDRIA Paul", "Ambodivoara"),
)

val collectes = listOf(
    Collecte(vanille, producteurs[0], 4.5,  "2026-08-01"),
    Collecte(cafe,    producteurs[1], 20.0, "2026-08-01"),
    Collecte(vanille, producteurs[2], 6.0,  "2026-08-01"),
    Collecte(girofle, producteurs[0], 15.0, "2026-08-02"),
    Collecte(litchi,  producteurs[1], 30.0, "2026-08-02"),
    Collecte(cafe,    producteurs[2], 22.0, "2026-08-02"),
    Collecte(vanille, producteurs[0], 8.0,  "2026-08-03"),
    Collecte(litchi,  producteurs[2], 25.0, "2026-08-03"),
)

// ----------------------------------------------------------------------------
// PARTIE A / B — CODE FOURNI, À LIRE PUIS À PRÉDIRE (rien à modifier ici)
// ----------------------------------------------------------------------------

/** Formate un montant en ariary : 1250000.0 -> "1 250 000 Ar" */
fun formatAriary(montant: Double): String {
    val entier = montant.toLong().toString()
    val groupes = entier.reversed().chunked(3).joinToString(" ").reversed()
    return "$groupes Ar"
}

/** Résumé d'une collecte, avec gestion du prix éventuellement absent. */
fun Collecte.resume(): String {
    val valeur = produit.prixKg?.let { formatAriary(poidsKg * it) } ?: "prix non fixé"
    return "${poidsKg} kg de ${produit.nom} (${producteur.nom}) — $valeur"
}

/** Les noms des produits collectés, sans doublon, triés. */
fun produitsCollectes(liste: List<Collecte>): List<String> =
    liste.map { it.produit.nom }.distinct().sorted()

/** Les collectes d'un village donné. */
fun collectesDuVillage(liste: List<Collecte>, village: String): List<Collecte> =
    liste.filter { it.producteur.village == village }

// ----------------------------------------------------------------------------
// PARTIE C — LES 5 TROUS À COMPLÉTER (un concept par trou)
// ----------------------------------------------------------------------------

/**
 * TROU n°1 — data class : copy().
 * Retourne une NOUVELLE collecte, identique à c, mais avec le poids corrigé.
 * La collecte d'origine ne doit pas être modifiée (les data classes sont
 * immuables : servez-vous de copy()).
 * Exemple attendu : corrigerPoids(c, 5.0).poidsKg == 5.0
 */
fun corrigerPoids(c: Collecte, nouveauPoidsKg: Double): Collecte {
    TODO("Trou n°1 — une ligne avec copy()")
}

/**
 * TROU n°2 — null safety.
 * Retourne poids × prix/kg, ou null si le prix n'est pas fixé.
 * INTERDIT : !!   AUTORISÉ : ?.  ?:  let
 * Exemples attendus :
 *   prixEstime(collectes[0]) == 1125000.0   (4.5 × 250000)
 *   prixEstime(collectes[4]) == null        (litchi : prix non fixé)
 */
fun prixEstime(c: Collecte): Double? {
    TODO("Trou n°2 — une ligne avec ?.")
}

/**
 * TROU n°3 — when.
 * Catégorie de poids d'une collecte :
 *   poids < 10.0        -> "petite"
 *   poids de 10.0 à 25.0 (inclus) -> "moyenne"
 *   au-delà             -> "grosse"
 * Utilisez une expression when (sans if).
 */
fun categorieDePoids(c: Collecte): String {
    TODO("Trou n°3 — une expression when")
}

/**
 * TROU n°4 — collections : agrégation.
 * Poids total collecté par produit (clé = nom du produit).
 * Exemple attendu sur le jeu de données :
 *   {Vanille=18.5, Café=42.0, Girofle=15.0, Litchi=55.0}
 * Indice : groupBy, puis mapValues + sumOf — en une expression.
 */
fun totalParProduit(liste: List<Collecte>): Map<String, Double> {
    TODO("Trou n°4 — groupBy + mapValues/sumOf")
}

/**
 * TROU n°5 — collections : tri filtré.
 * Les collectes VALORISABLES (prix connu), triées par valeur estimée
 * décroissante.
 * Indice : filter (ou mapNotNull) + sortedByDescending, en réutilisant
 * prixEstime (trou n°2).
 */
fun collectesValorisables(liste: List<Collecte>): List<Collecte> {
    TODO("Trou n°5 — filter + sortedByDescending")
}

/**
 * BONUS ★ — pour ceux qui ont terminé.
 * Le producteur le plus actif en poids total collecté (ou null si la liste
 * est vide). Indice : groupBy + maxByOrNull.
 */
fun producteurLePlusActif(liste: List<Collecte>): Producteur? {
    TODO("Bonus ★ — groupBy + maxByOrNull")
}

// ----------------------------------------------------------------------------
// VÉRIFICATION DES TROUS — décommentez l'appel dans main() au fur et à mesure
// ----------------------------------------------------------------------------

fun verifierTrous() {
    println("--- Vérification des trous ---")
    println("T1  poids corrigé : " + corrigerPoids(collectes[0], 5.0).poidsKg + "   (attendu : 5.0)")
    println("T1  original intact : " + collectes[0].poidsKg + "   (attendu : 4.5)")
    println("T2  vanille : " + prixEstime(collectes[0]) + "   (attendu : 1125000.0)")
    println("T2  litchi  : " + prixEstime(collectes[4]) + "   (attendu : null)")
    println("T3  " + collectes[0].poidsKg + " kg -> " + categorieDePoids(collectes[0]) + "   (attendu : petite)")
    println("T3  " + collectes[5].poidsKg + " kg -> " + categorieDePoids(collectes[5]) + "   (attendu : moyenne)")
    println("T4  " + totalParProduit(collectes) + "   (attendu : {Vanille=18.5, Café=42.0, Girofle=15.0, Litchi=55.0})")
    println("T5  " + collectesValorisables(collectes).map { it.resume() })
    // Bonus :
    // println("★   " + producteurLePlusActif(collectes)?.nom)
}

// ----------------------------------------------------------------------------
// MAIN — PARTIE B : les 4 appels à PRÉDIRE (P1..P4) avant exécution
// ----------------------------------------------------------------------------

fun main() {
    println("=== Coopérative — collectes du 01 au 03 août 2026 ===")

    // P1 — Prédisez la sortie exacte :
    println("P1: " + produitsCollectes(collectes))

    // P2 — Prédisez la sortie exacte (attention au litchi) :
    println("P2: " + collectes[4].resume())

    // P3 — Prédisez la sortie exacte :
    println("P3: " + collectesDuVillage(collectes, "Ambodivoara").size + " collectes à Ambodivoara")

    // P4 — Prédisez la sortie exacte (lisez bien formatAriary) :
    println("P4: " + formatAriary(1_250_000.0))

    // Une fois les trous complétés, décommentez :
    // verifierTrous()
}
