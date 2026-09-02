# Mini-TP 2 — Les coroutines — « Prédire puis vérifier »
*ITUniversity — Module M1 · Développement mobile Kotlin — Séance 2*
*Travail individuel, sur trois programmes fournis (`suspend`, `launch`, `async`/`await`)*

> ⚠️ **Les trois programmes** (`Programme1.kt`, `Programme2.kt`, `Programme3.kt`) ne sont **pas** inclus dans ce récapitulatif : ils sont fournis séparément dans `MiniTP2_Programmes.zip` sur le Drive de la séance. Télécharge-les et ajoute-les à ce dossier de travail.

---

## 1. Objectifs

- Prédire l'ordre des affichages et la durée de programmes à coroutines avant de les exécuter.
- Expliquer les écarts entre prédiction et réalité.
- Transformer un programme concurrent en séquentiel — et l'inverse — par simple déplacement des `await`.
- Reformuler avec ses propres mots une explication fournie par l'IA.

---

## 2. Règles du mini-TP

1. Les prédictions s'écrivent sur la feuille **AVANT toute exécution** des programmes : lire, prédire, puis seulement exécuter — c'est l'ordre des étapes qui fait tout l'intérêt du mini-TP.
2. Pendant les étapes 1 à 3, **aucune assistance IA** — complétion IA de l'IDE désactivée.
3. Un écart entre la prédiction et le résultat n'est pas une faute : c'est l'information la plus utile de la séance. Être honnête dans le tableau.

---

## 3. Étape 1 — Lire et prédire (sur papier, avant toute exécution)

Télécharger `MiniTP2_Programmes.zip` depuis le dossier Drive de la séance, le décompresser, et **ouvrir** les trois programmes dans Android Studio pour les **lire** — sans les exécuter. Pour chacun, remplir le modèle ci-dessous : l'ordre exact des affichages, la durée totale approximative, et une phrase d'explication. Ne rien lancer avant d'avoir rempli les trois lignes du modèle.

| Programme | Ordre EXACT des affichages prédits | Durée totale estimée | Pourquoi (une phrase) |
|---|---|---|---|
| **Programme 1 (launch)** | | | |
| **Programme 2 (async concurrent)** | | | |
| **Programme 3 (le piège)** | | | |

---

## 4. Étape 2 — Exécuter et expliquer les écarts

4. Lancer maintenant chaque programme avec la flèche verte à côté de son `main()` (laisser Gradle finir sa synchronisation à la première ouverture).
5. Comparer chaque sortie et chaque durée aux prédictions, et remplir le tableau ci-dessous.

| Programme | Écart constaté (ou « aucun ») | Explication en une phrase |
|---|---|---|
| **Programme 1** | | |
| **Programme 2** | | |
| **Programme 3** | | |

---

## 5. Étape 3 — Transformer (un déplacement d'`await` chacun)

**Transformation 1 :** rendre le **programme 2 SÉQUENTIEL** (chaque tâche attend la fin de la précédente). Ré-exécuter, et noter la durée mesurée dans le commentaire prévu en tête du fichier. Vérifier qu'elle correspond à la **somme** des délais.

**Transformation 2 :** rendre le **programme 3 CONCURRENT** (les deux tâches courent en même temps). Ré-exécuter, noter la durée dans le commentaire prévu. Vérifier qu'elle correspond au **maximum** des délais.

> Indice unique pour les deux : seule la position des `.await()` change. Si vous ajoutez ou retirez autre chose, c'est que vous cherchez trop loin.

---

## 6. Voie ouverte — une tâche IA unique

6. Choisir UN écart du tableau (le plus surprenant).
7. Demander à l'IA de votre choix d'expliquer le comportement réel du programme concerné. Prompt suggéré :

   > « Voici un programme Kotlin à coroutines et ce qu'il affiche. Explique pourquoi cet ordre et cette durée. » *(coller le programme et sa sortie)*

8. Dans `JOURNAL-IA.md`, reformuler cette explication **avec ses propres mots**, en trois lignes maximum — **sans recopier une seule phrase de l'IA**. La reformulation est le livrable ; la réponse brute de l'IA ne vaut rien ici.

---

## 7. Livrables

Tout se dépose en fin de séance dans le formulaire unique **« S2 · Dépôt des livrables »** :
🔗 https://forms.gle/jaLym8tmaDcoHVCa6
(le lien est aussi dans le dossier Drive de la séance)

Contenu du dépôt :

- `Programme2.kt` et `Programme3.kt` transformés (champs « Importer un fichier »), durées mesurées renseignées dans les commentaires **ET** dans les deux champs « Durée mesurée » du formulaire ;
- la feuille avec le tableau des écarts rempli, en photo ou PDF (champ « Importer un fichier ») ;
- la reformulation en trois lignes, recopiée directement dans le champ « JOURNAL-IA » du formulaire.

---

## 8. Annexe — Brouillon du journal IA (à recopier dans le formulaire)

```markdown
# Brouillon — à rédiger ici puis recopier dans le champ du formulaire

- Écart choisi (programme et nature de l'écart) : ...
- Explication reformulée avec mes mots (3 lignes max) : ...
```

---

## 9. Repères théoriques — résumé des slides « Coroutines »

### Pourquoi l'asynchrone est inévitable en mobile

- **Un seul thread pour l'interface** : il dessine l'écran et reçoit les gestes. S'il attend, l'écran gèle — et le système propose de tuer l'application (ANR).
- **Les attentes sont partout** : réseau, base de données, fichiers, calculs. En mobile, on attend en permanence — il faut organiser l'attente, pas l'éviter.
- **La réponse : les coroutines** — des tâches légères qui se suspendent pendant l'attente et rendent le thread, puis reprennent exactement où elles en étaient.

> Règle d'or du mobile : rien ne doit jamais bloquer le thread de l'interface.

### Thread vs coroutine — le guichet et le client

| | Thread (guichet) | Coroutine (client) |
|---|---|---|
| Fourni par | Le système d'exploitation | Kotlin — par-dessus les threads |
| Coût | ≈ 1 Mo chacun ; parallélisme réel plafonné aux cœurs (≈8), commutation système coûteuse | Quasi gratuite ; bascule = simple opération mémoire, des milliers sur 8 threads |
| Pendant une attente | Dort en tenant sa place | Descend du thread et le libère — puis reprend où elle s'était arrêtée |

### `suspend` : la fonction qui sait attendre

```kotlin
suspend fun chargerProduits(): List<String> {
    println("Téléchargement...")
    delay(1000)   // suspend, ne bloque pas
    return listOf("Vanille", "Café")
}
```

```kotlin
fun main() = runBlocking {
    val produits = chargerProduits()
    println(produits)
}
// runBlocking : crée la coroutine de nos programmes console.
// Dans Android : le ViewModel s'en chargera (séance 6).
```

> `delay` suspend la coroutine — `Thread.sleep` bloquerait le thread. Toute la différence est là.

### `suspend` · `launch` · `async` — une capacité, deux démarreurs

| | `suspend` | `launch` | `async` |
|---|---|---|---|
| Nature | Mot-clé — une **capacité** : la fonction peut se mettre en pause sans bloquer | Fonction de `kotlinx.coroutines` — « lancer et oublier » | Fonction de `kotlinx.coroutines` — « lancer et récupérer » |
| Retourne | La valeur de la fonction, normalement | Un `Job` (annuler, attendre la fin) — pas de valeur | Un `Deferred<T>` — encaissé avec `.await()` |
| Quand | Toute fonction qui attend : réseau, base de données, `delay` | Effet sans résultat attendu : journaliser, synchroniser en fond | Résultat attendu, souvent en parallèle d'autres tâches |

> Analogie banque : `suspend` = savoir patienter au guichet · `launch` = « va ranger le bureau » (aucun rapport attendu) · `async` = « va vérifier mon compte » (je viendrai chercher le chiffre avec `await`).

### Qui appelle une fonction `suspend` ? Quatre façons, un seul critère

1. **Appel direct**, déjà dans une coroutine → résultat tout de suite, dans l'ordre.
2. **Depuis un `launch`** → effet en arrière-plan, résultat ignoré.
3. **Depuis un `async`** → résultat récupéré en parallèle avec `.await()`.
4. **Depuis une autre fonction `suspend`** → la chaîne remonte, une coroutine porte le tout en haut.

> Le critère n'est pas dans la fonction — il est dans le besoin : résultat immédiat → appel direct · pas de résultat → `launch` · résultat en parallèle → `async`.
> `runBlocking` est un démarreur de plus, mais qui **bloque** le thread : réservé au pont console/tests, jamais dans Android.

### Lancer plusieurs coroutines

```kotlin
// launch — lancer et oublier
fun main() = runBlocking {
    println("A")
    launch {
        delay(500)
        println("B")
    }
    println("C")
}
// Quel ordre d'affichage ? → Programme 1 du mini-TP.
```

```kotlin
// async / await — lancer et récupérer
fun main() = runBlocking {
    val a = async { delay(1000); "produits" }
    val b = async { delay(1500); "collectes" }
    println(a.await() + " + " + b.await())
}
// Les deux courent EN MÊME TEMPS : durée totale = max(1000, 1500) ≠ 1000 + 1500
```

> Le piège de la semaine : l'endroit où l'on place `await` change tout — Programme 3 du mini-TP.

### Séquentiel vs concurrent

```kotlin
// Séquentiel
val t = measureTimeMillis {
    val a = async { delay(1000); "P" }.await()   // attend ici
    val b = async { delay(1500); "C" }.await()   // puis là
    println("$a $b")
}
println("Durée : $t ms")
```

```kotlin
// Concurrent
val t = measureTimeMillis {
    val a = async { delay(1000); "P" }
    val b = async { delay(1500); "C" }
    // les deux courent déjà...
    println(a.await() + " " + b.await())
}
println("Durée : $t ms")
```

> Deux codes presque identiques — c'est la position des `await` qui fait la concurrence.

### Pour la suite du module (aperçu, hors mini-TP)

- **Dispatchers** — où s'exécute la coroutine : `Dispatchers.Main` (thread de l'interface), `Dispatchers.IO` (réseau, fichiers, base de données). À retenir : « le travail lourd ailleurs, le résultat sur Main ».
- **Flow** — pas une valeur mais un flux de valeurs dans le temps ; se collecte, chaque émission redéclenche le traitement ; alimentera l'interface réactive (`StateFlow`, séance 6).

---

## 10. Déroulé de la séance (résumé slides)

| Étape | Contenu |
|---|---|
| 1 — Prédire | Test « S2 · Prédictions » : ordre exact des affichages + durée estimée, pour chacun des 3 programmes |
| 2 — Exécuter et expliquer | Comparer aux prédictions ; expliquer chaque écart par écrit |
| 3 — Transformer | Programme 2 → séquentiel et mesurer. Programme 3 → concurrent et mesurer. Un déplacement d'`await` chacun |
| 4 — Voie ouverte, juger l'IA | Faire expliquer UN écart par l'IA, puis reformuler avec ses propres mots (3 lignes au journal) |
