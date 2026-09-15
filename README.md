# Mobile M1 — Mini-TP Kotlin / Android

Ce dépôt regroupe les mini-travaux pratiques (mini-TP) réalisés dans le cadre du module **Développement mobile Kotlin** (Master 1, ITUniversity / AP-DIRO). Chaque mini-TP est développé sur sa propre branche (`minitp1` à `minitp7`) et fait progresser le même fil rouge : une application de gestion pour une **coopérative agricole malgache** (produits, producteurs, collectes).

La progression suit une logique pédagogique cumulative :

1. Kotlin pur (sans Android) → bases du langage et des collections.
2. Coroutines → programmation asynchrone.
3. Première application Android (Activities, cycle de vie, Intents).
4. Jetpack Compose → UI déclarative et état local.
5. Navigation entre écrans.
6. Architecture MVVM → état centralisé et persistant à la rotation.
7. Persistance avec Room → base de données locale « offline-first ».

Chaque TP est fourni avec un énoncé (`*_Enonce_etudiants.docx`), une fiche de récapitulatif théorique, et un `JOURNAL-IA*.md` documentant l'usage encadré de l'IA (analyse d'un écart ou d'un comportement, reformulée avec ses propres mots).

---

## Mini-TP 1 — Kotlin essentiel : « Lire et transformer »

**Branche :** `minitp1` · **Techno :** Kotlin (script `main()`, sans Android)

**Objectif :** se familiariser avec la syntaxe Kotlin idiomatique (par rapport à Java) à travers un exercice en trois temps : lire, prédire, compléter.

**Notions mises en œuvre :**
- `data class`, `copy()` (immutabilité)
- Null-safety : `?.`, `?:`, `let` (opérateur `!!` interdit)
- Fonctions d'extension (`Collecte.resume()`)
- Expressions `when`
- Opérations sur les collections : `map`, `filter`, `distinct`, `sorted`, `groupBy`, `mapValues`, `sumOf`, `sortedByDescending`, `maxByOrNull` (boucles `for`/`while` interdites)

**Étapes réalisées :**
- **Partie A (Lire) :** annotation de trois constructions du fichier fourni (`formatAriary`, fonction d'extension avec safe-call, etc.).
- **Partie B (Prédire) :** prédiction de la sortie exacte de 4 appels (`P1`–`P4`) avant exécution, consignée dans `predictions.md`/`predictions.html`/`predictions.pdf`.
- **Partie C (Compléter) :** résolution de 5 « trous » dans `Collectes.kt` — `corrigerPoids` (copy), `prixEstime` (null-safety), `categorieDePoids` (when), `totalParProduit` (agrégation par groupBy), `collectesValorisables` (filtre + tri).
- **Bonus :** `producteurLePlusActif` (groupBy + maxByOrNull).

**Résultat obtenu :** toutes les prédictions se sont vérifiées sans écart (`predictions.md`) ; le fichier `Collectes.kt` compile et s'exécute, `verifierTrous()` valide les 5 fonctions et le bonus.

```kotlin
fun totalParProduit(liste: List<Collecte>): Map<String, Double> =
    liste.groupBy { it.produit.nom }
        .mapValues { (_, collectes) -> collectes.sumOf { it.poidsKg } }
```

---

## Mini-TP 2 — Les coroutines : « Prédire puis vérifier »

**Branche :** `minitp2` · **Techno :** Kotlin + `kotlinx.coroutines` (script console, `runBlocking`)

**Objectif :** comprendre `suspend`, `launch` et `async`/`await`, et la différence entre exécution séquentielle et concurrente.

**Notions mises en œuvre :**
- `suspend` (fonction qui peut se suspendre sans bloquer un thread)
- `launch` (« lancer et oublier », retourne un `Job`)
- `async` / `await` (« lancer et récupérer », retourne un `Deferred<T>`)
- Mesure de durée (`measureTimeMillis`)
- Différence thread (coûteux, système) vs coroutine (légère, gérée par Kotlin)

**Étapes réalisées :**
- **Étape 1 :** lecture et prédiction sur papier (ordre des affichages, durée) de trois programmes fournis, *avant* toute exécution.
- **Étape 2 :** exécution des trois programmes et explication des écarts entre prédiction et réalité.
  - `Programme1.kt` : `launch` → démontre que le code après un `launch` s'exécute avant la coroutine lancée (ordre A, C, puis B après 500 ms).
  - `Programme2.kt` : deux `async` suivis chacun d'un `.await()` immédiat → exécution **séquentielle** malgré `async` (durée ≈ somme des délais).
  - `Programme3.kt` : le « piège » — deux `async` lancés puis `.await()` tous les deux ensuite → exécution **concurrente** (durée ≈ max des délais).
- **Étape 3 (Transformer) :** un seul déplacement d'`await` par programme.
  - `Programme2.kt` rendu **séquentiel → concurrent** : durée mesurée après transformation **2580 ms**.
  - `Programme3.kt` rendu **concurrent → séquentiel** (sens inverse demandé) : durée mesurée après transformation **1040 ms**.
- **Étape 4 :** un écart expliqué par l'IA puis reformulé avec ses propres mots dans `JOURNAL-IA.md`, tableau récapitulatif dans `ECARTS.md`.

**Résultat obtenu :** mise en évidence concrète que la concurrence ne dépend pas de `async` seul mais de la **position des `.await()`**.

```kotlin
// Concurrent : les deux async démarrent avant qu'on attende un résultat
val poidsVanille = async { delay(1000); 4.5 }
val poidsCafe    = async { delay(800);  6.0 }
println("Poids total : " + (poidsVanille.await() + poidsCafe.await()) + " kg")
```

---

## Mini-TP 3 — Anatomie Android : « Observer le cycle de vie »

**Branche :** `minitp3` · **Projet :** `cycledevie` · **Techno :** Android natif (Kotlin, `AppCompatActivity`, vues XML)

**Objectif :** observer et comprendre le cycle de vie d'une `Activity`, et compléter un `Intent` implicite.

**Notions mises en œuvre :**
- Callbacks du cycle de vie : `onCreate`, `onStart`, `onResume`, `onPause`, `onStop`, `onRestart`, `onDestroy`
- Journalisation via `Log.i` filtrable au Logcat (tags `CYCLE` et `CYCLE-2`)
- Intents **explicites** (`Intent(this, SecondActivity::class.java)`) vs **implicites** (`Intent.ACTION_SEND`)
- Entrelacement du cycle de vie entre deux `Activity`

**Étapes réalisées :**
- **Étape 1 (Prédire) :** prédiction sur papier de la séquence exacte de callbacks pour deux scénarios : rotation d'écran, et mise en arrière-plan (bouton accueil) puis retour.
- **Étape 2 (Observer) :** exécution réelle sur émulateur/appareil, comparaison au Logcat (filtre `tag:CYCLE`), mise en évidence que la **rotation détruit et recrée** l'`Activity` (numéro d'instance différent → tout état non sauvegardé est perdu).
- **Bonus :** ouverture d'un second écran (`SecondActivity`) pour observer l'entrelacement des callbacks des deux Activities.
- **Étape 3 (Compléter) :** implémentation de `partagerCollecte()` — un `Intent` implicite `ACTION_SEND` (type `text/plain`) ouvrant le sélecteur de partage du système.

**Résultat obtenu :** application « CycleDeVie » avec deux écrans et un bouton de partage fonctionnel ; comportements du cycle de vie consignés dans `mini_tp3_feuille_repondue.pdf` et `logcat_cycle_de_vie.pdf`.

```kotlin
private fun partagerCollecte() {
    val intent = Intent(Intent.ACTION_SEND)
        .setType("text/plain")
        .putExtra(Intent.EXTRA_TEXT, "Collecte du jour : 4,5 kg de vanille")
    startActivity(Intent.createChooser(intent, null))
}
```

---

## Mini-TP 4 — Jetpack Compose : « Faire vivre un écran »

**Branche :** `minitp4` · **Projet :** `carteproduit` · **Techno :** Jetpack Compose (Material 3)

**Objectif :** découvrir l'UI déclarative et l'état local géré avec `remember`/`mutableStateOf`, et observer la recomposition.

**Notions mises en œuvre :**
- Fonctions `@Composable`
- État local : `remember { mutableStateOf(...) }`
- Recomposition (journalisée via `Log.i("RECOMP", ...)`, filtrable au Logcat)
- Composants Material 3 : `Card`, `Button`, `Text`, `Column`, `Surface`
- Modificateurs (`Modifier.clickable`, `.padding`, `.fillMaxWidth`)

**Étapes réalisées :**
- **TODO A :** compteur de quantité (`quantite` en `remember`), incrémenté par un bouton « Ajouter 1 kg ».
- **TODO B :** carte sélectionnable — un clic sur la `Card` bascule un booléen `selectionnee` qui change la couleur de fond (`primaryContainer` vs `surfaceVariant`).
- Observation au Logcat (tag `RECOMP`) du nombre de recompositions déclenchées par chaque interaction.

**Résultat obtenu :** écran « carte produit » avec compteur de quantité et sélection visuelle fonctionnels, capture du Logcat après manipulation (`Logcatapres.png`).

```kotlin
var quantite by remember { mutableStateOf(0) }
var selectionnee by remember { mutableStateOf(false) }
```

---

## Mini-TP 5 — Navigation : « Relier deux écrans »

**Branche :** `minitp5` · **Projet :** `listedetail` · **Techno :** Jetpack Compose + Navigation Compose

**Objectif :** relier un écran de liste et un écran de détail avec `NavHost`, en passant un argument dans la route.

**Notions mises en œuvre :**
- `NavHostController`, `rememberNavController()`
- `NavHost` / `composable(route)` avec argument de route (`"detail/{produitId}"`)
- `navController.navigate(...)` et `popBackStack()`
- `LazyColumn` + `items()` pour une liste performante

**Étapes réalisées :**
- Écriture de `AppNavigation()` : déclaration des deux destinations (`"liste"` et `"detail/{produitId}"`).
- Récupération de l'argument `produitId` depuis le `backStackEntry`, recherche du produit correspondant.
- Câblage du clic sur une carte de la liste (`onProduitClick`) vers `navController.navigate("detail/$produitId")`.
- Câblage du bouton « Retour à la liste » vers `navController.popBackStack()`.

**Résultat obtenu :** application à deux écrans pleinement navigable (liste de 5 produits de la coopérative → détail d'un produit → retour), écrans de liste et de détail fournis et fonctionnels.

```kotlin
composable("detail/{produitId}") { backStackEntry ->
    val id = backStackEntry.arguments?.getString("produitId")?.toIntOrNull()
    val produit = produits.find { it.id == id }
    ...
}
```

---

## Mini-TP 6 — Architecture MVVM : « Compléter la couche manquante »

**Branche :** `minitp6` · **Projet :** `listedetailv2` · **Techno :** Jetpack Compose + `ViewModel` + `StateFlow`

**Objectif :** faire survivre l'état à la rotation d'écran et aux allers-retours de navigation en le sortant de la composition (`remember`) pour le porter dans un `ViewModel` partagé.

**Notions mises en œuvre :**
- `ViewModel` (`androidx.lifecycle.ViewModel`)
- `MutableStateFlow` / `StateFlow`, `update {}`
- `collectAsState()` pour observer le flux depuis la Compose UI
- Flux unidirectionnel : **l'état descend** (`uiState`), **les événements remontent** (appels de fonctions du ViewModel)
- Un seul `ViewModel` partagé entre les deux écrans via `viewModel()` au niveau de `AppNavigation()`

**Étapes réalisées :**
- Complétion de `ProduitsViewModel` : `EtatUi` (data class d'état), `MutableStateFlow(EtatUi(...))`, méthode `ajouterAuPanier(poidsKg)` mettant à jour l'état via `_uiState.update { ... }`.
- Câblage des deux écrans sur `viewModel.uiState.collectAsState()` pour afficher le poids du panier partagé.
- **« Casser le flux » (étape 3) :** ajout volontaire d'une variable `poidsTriche` **hors** du circuit état → observation → recomposition, pour constater qu'elle ne survit pas à la rotation contrairement à l'état du `ViewModel`.

**Résultat obtenu :** un panier dont le contenu (poids ajouté) persiste à travers la navigation liste ↔ détail et à travers une rotation d'écran, contrairement au compteur « triche » géré en dehors du `ViewModel`.

```kotlin
class ProduitsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(EtatUi(produits = produits))
    val uiState: StateFlow<EtatUi> = _uiState

    fun ajouterAuPanier(poidsKg: Int) {
        _uiState.update { etat -> etat.copy(poidsPanierKg = etat.poidsPanierKg + poidsKg) }
    }
}
```

---

## Mini-TP 7 — Room : « Trois requêtes » (offline-first)

**Branche :** `minitp7` · **Projet :** `listedetailv3` · **Techno :** Jetpack Compose + Room + `AndroidViewModel` + `Flow`

**Objectif :** remplacer les données en mémoire par une base de données locale persistante (Room), avec un état d'écran alimenté en temps réel par plusieurs requêtes combinées.

**Notions mises en œuvre :**
- Room : `@Entity`, `@Dao`, `@Database`, `@Query`, `@Insert`
- Requêtes retournant un `Flow<T>` (ré-émission automatique à chaque changement de la base)
- `combine()` de plusieurs `Flow` en un seul état d'écran
- `stateIn(scope, SharingStarted.WhileSubscribed(5000), initialValue)`
- `AndroidViewModel` (accès au `Context` applicatif pour instancier la base)
- Singleton de base de données (`@Volatile` + `synchronized`)

**Étapes réalisées (3 TODO dans le DAO `ProduitDao`) :**
- **TODO 1 — Tri :** produits triés par prix décroissant, les prix `null` (non fixés) placés en dernier : `ORDER BY prixKg IS NULL, prixKg DESC`.
- **TODO 2 — Filtre :** produits dont le stock dépasse un seuil paramétrable : `WHERE stockKg > :seuilKg`.
- **TODO 3 — Agrégat :** stock total de tous les produits (peut être `null` si la table est vide) : `SELECT SUM(stockKg) FROM produits`.
- Dans le `ViewModel`, les quatre `Flow` (tri par nom, tri par prix, filtre stock, stock total) et le mode d'affichage courant sont fusionnés avec `combine()` pour produire un unique `EtatUi` observé par l'écran.
- Insertion du jeu de données initial au premier lancement si la base est vide.

**Résultat obtenu :** application « offline-first » où la liste de produits est stockée en base SQLite (via Room) et se met à jour dynamiquement selon le mode d'affichage choisi (par nom, par prix décroissant, ou stock suffisant) ; captures des trois tris dans `image/tri-nom.jpg`, `image/tri-prix.jpg`, `image/tri-stock.jpg`.

```kotlin
@Query("SELECT * FROM produits ORDER BY prixKg IS NULL, prixKg DESC")
fun parPrixDecroissant(): Flow<List<Produit>>

@Query("SELECT * FROM produits WHERE stockKg > :seuilKg")
fun stockSuperieurA(seuilKg: Double): Flow<List<Produit>>

@Query("SELECT SUM(stockKg) FROM produits")
fun stockTotal(): Flow<Double?>
```

---

## Progression d'ensemble

| Mini-TP | Thème | Techno clé | Ce qui persiste au TP suivant |
|---|---|---|---|
| 1 | Kotlin essentiel | Collections, null-safety | Le domaine « coopérative » (produits, producteurs, collectes) |
| 2 | Coroutines | `suspend`, `launch`, `async` | La compréhension de l'asynchrone, réutilisée dès le TP 7 |
| 3 | Cycle de vie Android | Activities, Intents | La notion qu'un état non externalisé ne survit pas à une reconfiguration |
| 4 | Compose — état local | `remember`, recomposition | Les composants d'UI (carte produit) |
| 5 | Navigation | `NavHost`, routes | La structure liste/détail, réutilisée aux TP 6 et 7 |
| 6 | MVVM | `ViewModel`, `StateFlow` | L'état centralisé, remplacé par une source Room au TP 7 |
| 7 | Persistance | Room, `Flow`, `combine` | Application finale « offline-first » |
