# Mini-TP 4 — Jetpack Compose — « Faire vivre un écran »
*ITUniversity — Module M1 · Développement mobile Kotlin — Séance 4*
*État, remember, recomposition — travail individuel, sur le projet fourni « CarteProduit »*

> ⚠️ **Le projet source** (`MainActivity.kt` avec la carte statique, le log `RECOMP` et les deux `TODO` en commentaire) n'est **pas** inclus dans ce récapitulatif : il est fourni séparément dans `MiniTP4_CarteProduit.zip` sur le Drive de la séance. Télécharge-le et ouvre-le dans Android Studio.

---

## 1. Objectifs

- Prédire puis observer la recomposition — la voir se produire, log à l'appui.
- Ajouter un état avec `remember` + `mutableStateOf` (le compteur de quantité).
- Rendre un composable réactif au clic (la carte sélectionnable).
- Porter un jugement de conception sur une variante proposée par l'IA.

---

## 2. Règles du mini-TP

1. Les prédictions se remplissent dans le modèle de l'étape 1 **AVANT tout lancement** de l'application : lire, prédire, puis seulement exécuter.
2. Pendant les étapes 1 à 3, **aucune assistance IA** — complétion IA de l'IDE désactivée.
3. Le log `RECOMP` est déjà en place dans le projet : ne pas le déplacer, c'est l'instrument de mesure.

---

## 3. Étape 1 — Lire et prédire (sur papier, avant tout lancement)

Télécharger `MiniTP4_CarteProduit.zip` depuis le dossier Drive de la séance, le décompresser, ouvrir le projet dans Android Studio (`File → Open`) et **lire** `MainActivity.kt` — la carte statique, le log `RECOMP`, les deux `TODO` en commentaire. Puis remplir le modèle :

| Prédiction | Votre nombre | Pourquoi (une phrase) |
|---|---|---|
| **P1** — Au premier affichage de l'écran, combien de lignes `RECOMP` au Logcat ? | | |
| **P2** — Une fois le TODO A fait : combien de NOUVELLES lignes `RECOMP` après 3 clics sur « Ajouter 1 kg » ? | | |

> Ne rien lancer avant d'avoir rempli les deux lignes du modèle.

---

## 4. Étape 2 — TODO A : le compteur de quantité

4. Suivre le commentaire `TODO A` du fichier : déclarer l'état (`remember` + `mutableStateOf`), puis brancher le `Text` et le `Button`. Le modèle exact est sur la diapositive « L'état » du cours (voir §9 ci-dessous).
5. Lancer l'application, filtrer le Logcat avec : `tag:RECOMP`
6. Vérifier la prédiction P1 (lignes au démarrage), puis cliquer 3 fois sur « Ajouter 1 kg » et vérifier P2. Noter les écarts et leur explication au dos de la feuille.

**Question de contrôle** (une phrase au dos de la feuille) : retirer mentalement le `remember` (sans le faire) — que deviendrait le compteur à chaque clic, et pourquoi ?

---

## 5. Étape 3 — TODO B : la carte sélectionnable

7. Suivre le commentaire `TODO B` : un second état booléen, `Modifier.clickable`, et la couleur de la carte qui change selon l'état.
8. Vérifier : un clic sur la carte change sa couleur ET produit une ligne `RECOMP`. Capturer le Logcat montrant les recompositions (démarrage + clics) : cette capture est un livrable.

**Observation bonus :** tourner l'écran. Que deviennent la quantité et la sélection — et quelle séance du module l'avait annoncé ?

---

## 6. Voie ouverte — une tâche IA unique : juger une variante

9. Demander à l'IA de votre choix UNE variante de mise en page de votre carte. Prompt suggéré :

   > « Voici un composable Kotlin. Propose UNE variante de mise en page (par exemple en Row), sans ajouter de fonctionnalité. » *(coller votre `ProduitCard`)*

10. Comparer les deux versions et rendre un jugement en trois lignes : laquelle garder, et pourquoi — lisibilité du code, cohérence visuelle, simplicité. Recopier ce jugement dans le champ « JOURNAL-IA » du formulaire de dépôt.

> Garder SA version avec de bonnes raisons est un résultat parfaitement valable.

---

## 7. Livrables (formulaire « S4 · Dépôt des livrables »)

Tout se dépose en fin de séance dans le formulaire unique « S4 · Dépôt des livrables » — le lien est affiché en séance et dans le dossier Drive de la séance :

- cette feuille remplie (modèle de prédictions, écarts, question de contrôle, observation bonus), en photo ou PDF ;
- la capture du Logcat filtré sur `RECOMP` (démarrage + clics) ;
- le projet avec les deux TODO aboutis, en URL Git ;
- le jugement en trois lignes sur la variante, recopié dans le champ « JOURNAL-IA » du formulaire.

---

## 8. Repères théoriques — résumé des slides « Jetpack Compose »

### XML vs Jetpack Compose : la même carte produit

**XML + Activity — 2 langages, 2 fichiers**

```xml
<!-- res/layout/produit_card.xml -->
<LinearLayout android:orientation="vertical">
    <TextView android:id="@+id/tvNom" .../>
    <TextView android:id="@+id/tvPrix" .../>
</LinearLayout>
```
```kotlin
// ProduitActivity.kt
val tvNom = findViewById<TextView>(R.id.tvNom)
val tvPrix = findViewById<TextView>(R.id.tvPrix)
tvNom.text = produit.nom
tvPrix.text = formatPrix(produit.prixKg)
// donnée modifiée ? → resynchroniser chaque vue À LA MAIN
```

**Jetpack Compose — 1 langage, 1 fonction**

```kotlin
@Composable
fun ProduitCard(produit: Produit) {
    Column(Modifier.padding(16.dp)) {
        Text(
            produit.nom,
            style = MaterialTheme.typography.titleMedium
        )
        Text(produit.prixKg?.let { "$it Ar/kg" } ?: "prix non fixé")
    }
}
// état modifié ? → recomposition AUTOMATIQUE
```

> On ne décrit plus comment mettre l'écran à jour — on déclare ce que l'écran doit être : l'UI est une fonction de l'état.

### Le paradigme : décrire l'écran, pas les mises à jour

1. **Un écran = une fonction** — annotée `@Composable` : elle reçoit des données et décrit l'interface pour ces données-là.
2. **L'état change → recomposition** — Compose rappelle la fonction. Pas de `findViewById`, pas de `setText` — pas d'oubli possible.
3. **Un bug disparaît par construction** — l'« écran pas à jour », classe de bugs la plus fréquente de l'Android historique, ne peut plus exister.

> « L'interface est une photographie de l'état. L'état change ? On reprend la photo. »

### Les briques : composables, conteneurs, Modifier

```kotlin
@Composable
fun ProduitCard(produit: Produit) {
    Card(Modifier.padding(16.dp)) {
        Column(Modifier.padding(16.dp)) {
            Text(
                produit.nom,
                style = MaterialTheme.typography.titleLarge
            )
            Text(produit.prixKg?.let { "$it Ar/kg" } ?: "prix non fixé")
        }
    }
}
```

**Le Modifier — la chaîne de personnalisation :**

```kotlin
Modifier
    .padding(16.dp)     // espace
    .fillMaxWidth()     // largeur
    .clickable { ... }  // réagir au clic

// L'ORDRE COMPTE :
// padding puis clickable ≠ clickable puis padding
// (la zone cliquable change)
```

> La null safety de la séance 1 traverse jusque dans l'interface : un prix null ne peut pas s'afficher par accident.

### Le Modifier en action : une chaîne, un rendu

```kotlin
@Composable
fun ProduitCard(p: Produit, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()                                  // 1 — la carte occupe toute la largeur
            .padding(horizontal = 16.dp, vertical = 8.dp)     // 2 — espace entre la carte et le bord de l'écran
            .clickable { onClick() },                         // 3 — la carte réagit au toucher
    ) {
        Column(Modifier.padding(16.dp)) {                     // 4 — espace entre le bord de la carte et le texte
            Row(Modifier.fillMaxWidth()) {
                Text(p.nom,
                    modifier = Modifier.weight(1f),           // 5 — le titre prend la place restante, le stock est poussé à droite
                    style = titleLarge)
                Text("${p.stockKg} kg")
            }
            Text(p.prixKg?.let { "$it Ar/kg" } ?: "prix non fixé")
        }
    }
}
```

> L'ordre compte : padding AVANT clickable → la marge n'est pas cliquable · padding APRÈS clickable → la marge le devient. Chaque maillon enveloppe le précédent.

### L'état : `remember` + `mutableStateOf`

```kotlin
@Composable
fun ProduitCard(produit: Produit) {
    Log.i("RECOMP", "ProduitCard se (re)compose")

    var quantite by remember { mutableStateOf(0) }

    Column {
        Text("Quantité : $quantite kg")
        Button(onClick = { quantite++ }) {
            Text("Ajouter 1 kg")
        }
    }
}
```

**Les trois morceaux :**

- `mutableStateOf(0)` : un état OBSERVABLE — Compose sait qui en dépend.
- `remember` : survit aux recompositions (sans lui : retour à 0 à chaque fois).
- `by` : délégation Kotlin — se lit et s'écrit comme une variable.

```kotlin
// Fonctionne, mais plus verbeux (sans le "by")
val quantite = remember { mutableStateOf(0) }
Text("Quantité : ${quantite.value} kg")
Button(onClick = { quantite.value++ }) {
    Text("Ajouter 1 kg")
}
```

> `remember` survit aux recompositions — pas à la rotation (séance 3). La vraie survie a un nom : `ViewModel`, séance 6.

### Les listes : `LazyColumn`

```kotlin
@Composable
fun ListeProduits(produits: List<Produit>) {
    LazyColumn {
        items(produits) { p ->
            ProduitCard(p)   // réutilisée !
        }
    }
}
```

**À retenir :**

- « Lazy » : seuls les éléments visibles sont composés.
- `items(...)` : un composable par élément.
- `ProduitCard` réutilisée telle quelle — la composition, c'est aussi la réutilisation.
- Séance 5 : cette liste devient cliquable → écran de détail.

### Lire un layout XML

```xml
<!-- activity_main.xml — à savoir LIRE -->
<LinearLayout
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/tvNom"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>

    <Button android:id="@+id/btnAjouter" .../>
</LinearLayout>
```

**Table de correspondance mentale :**

| XML | Compose |
|---|---|
| `LinearLayout` vertical / horizontal | `Column` / `Row` |
| `TextView` / `Button` | `Text` / `Button` |
| `RecyclerView` | `LazyColumn` |
| `android:id` + `findViewById` | plus besoin : la fonction reçoit ses données |
| `match_parent` | `fillMaxWidth` / `fillMaxSize` |

> On ne l'écrit plus — on sait le lire. Et vous l'avez déjà lu : c'est le layout de `CycleDeVie`, séance 3.

---

## 9. Déroulé de la séance (résumé slides)

| Étape | Contenu |
|---|---|
| 1 — Lire et prédire | Lire `MainActivity.kt` (carte statique, log `RECOMP` en place) ; prédire sur la feuille : lignes `RECOMP` au démarrage, puis après 3 clics |
| 2 — TODO A : le compteur | `remember` + `mutableStateOf` + `Button`. Exécuter, cliquer 3 fois, compter les `RECOMP` au Logcat, expliquer l'écart |
| 3 — TODO B : la carte sélectionnable | Second état booléen, `Modifier.clickable`, couleur qui change. La capture du Logcat prouve la recomposition |
| 4 — Voie ouverte, juger l'IA | UNE variante de mise en page demandée à l'IA ; en 3 lignes : laquelle garder, et pourquoi |

> Dépôt en fin de séance : formulaire « S4 · Dépôt des livrables » — feuille, capture `RECOMP`, projet ZIP, jugement en 3 lignes.
