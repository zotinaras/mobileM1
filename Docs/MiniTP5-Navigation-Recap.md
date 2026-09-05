# Mini-TP 5 — Navigation — « Relier deux écrans »
*ITUniversity — Module M1 · Développement mobile Kotlin — Séance 5*
*NavHost, routes, arguments, retour — travail individuel, sur le projet fourni « ListeDetail »*

> ⚠️ **Le projet source** (`MainActivity.kt` avec les deux écrans fournis et fonctionnels, le `NavHost` et ses trois `TODO`) n'est **pas** inclus dans ce récapitulatif : il est fourni séparément dans `MiniTP5_ListeDetail.zip` sur le Drive de la séance. Télécharge-le et ouvre-le dans Android Studio.

---

## 1. Objectifs

- Compléter un `NavHost` : déclarer une route avec argument, naviguer, revenir.
- Faire voyager un identifiant de la liste vers le détail — et comprendre pourquoi l'identifiant plutôt que l'objet.
- Vérifier le comportement de la backstack avec le retour système (lien avec la séance 3).
- Trier les remarques d'une revue IA : pertinente ou non pertinente ici.

---

## 2. Règles du mini-TP

1. Les prédictions se remplissent dans le modèle de l'étape 1 **AVANT tout lancement** de l'application : lire, prédire, puis seulement exécuter.
2. Pendant les étapes 1 à 3, **aucune assistance IA** — complétion IA de l'IDE désactivée.
3. Les deux écrans sont fournis et fonctionnels : **SEULE** la fonction `AppNavigation()` est à modifier — trois `TODO`, rien d'autre.

---

## 3. Étape 1 — Lire et prédire (sur papier, avant tout lancement)

Télécharger `MiniTP5_ListeDetail.zip` depuis le dossier Drive de la séance, le décompresser, ouvrir le projet dans Android Studio (`File → Open`) et **lire** `MainActivity.kt` en entier — les deux écrans fournis, le `NavHost` et ses trois `TODO`. Puis remplir le modèle :

| Prédiction | Votre réponse |
|---|---|
| **P1** — Au lancement (AVANT tout TODO) : que fait un clic sur un produit de la liste, et pourquoi ? | |
| **P2** — Une fois les TODO faits : que fera le bouton retour SYSTÈME depuis l'écran de détail ? Et depuis la liste ? (souvenez-vous de la séance 3) | |

> Ne rien lancer avant d'avoir rempli les deux lignes du modèle.

---

## 4. Étape 2 — Les trois TODO, dans l'ordre

4. **TODO 1** — la route du détail : déclarer `composable("detail/{produitId}")` en recopiant le modèle fourni en commentaire — pas pour le deviner, pour le COMPRENDRE : à chaque ligne recopiée, se dire à voix basse ce qu'elle fait (déclarer l'argument, le relire en texte, le convertir, retrouver le produit, gérer le null).
5. **TODO 2** — naviguer au clic : une ligne dans `onProduitClick`.
6. **TODO 3** — le retour : une ligne dans `onRetour`.
7. Vérifier le circuit complet : liste → clic sur « Girofle » → le détail affiche bien le girofle (pas un autre !) → « Retour à la liste ».

---

## 5. Étape 3 — Observer la backstack

8. Depuis l'écran de détail, utiliser le retour SYSTÈME (geste ou touche du téléphone) : comparer à la prédiction P2.
9. Depuis la liste, utiliser encore le retour système : noter la différence sur la feuille, et l'expliquer en une phrase avec le vocabulaire de la pile.
10. **Cas limite :** ouvrir le détail du litchi (prix non fixé) — vérifier que l'écran l'affiche proprement. Quelle construction Kotlin de la séance 1 rend cela possible ? (une phrase sur la feuille)

---

## 6. Voie ouverte — une tâche IA unique : trier une revue

11. Soumettre la fonction `AppNavigation()` complétée à l'IA de votre choix. Prompt suggéré :

    > « Fais une revue de ce code de navigation Compose : liste tes remarques, ne réécris pas tout. »

12. Trier ensuite CHAQUE remarque en deux colonnes sur la feuille : pertinente / non pertinente ICI — avec un mot de justification. L'IA suggérera probablement des choses hors périmètre (routes typées, `ViewModel`, animations...) : les classer « non pertinentes ici » est exactement l'exercice. Recopier les deux ou trois lignes de synthèse de ce tri dans le champ « JOURNAL-IA » du formulaire.

---

## 7. Livrables (formulaire « S5 · Dépôt des livrables »)

Tout se dépose en fin de séance dans le formulaire unique « S5 · Dépôt des livrables » — le lien est affiché en séance et dans le dossier Drive de la séance :

- cette feuille remplie (modèle de prédictions, observations de la backstack, tri des remarques), en photo ou PDF ;
- le projet avec la navigation fonctionnelle, en URL Git ;
- la synthèse de votre tri des remarques IA, recopiée dans le champ « JOURNAL-IA » du formulaire.

Ces dépôts servent au suivi de votre progression. Les modalités d'évaluation du module vous seront précisées ultérieurement.

### Contenu exact du formulaire de dépôt

| Champ | Nature | Précision |
|---|---|---|
| Adresse e-mail | obligatoire | |
| Numéro ETU | obligatoire | ex. `ETU002026` |
| Dépôt 1 — la feuille (prédictions, observations backstack, tri des remarques) | URL public Drive | photo ou PDF |
| Le circuit complet fonctionne-t-il ? | obligatoire, choix unique | « Oui, circuit complet avec le bon produit et les deux retours » / « Partiellement (préciser sur la feuille ce qui manque) » / « Non, la navigation n'a pas abouti » |
| Retour système depuis la LISTE : qu'avez-vous observé ? | obligatoire, choix unique | « L'application se ferme (la pile était vide) » / « Retour à un autre écran de l'application » / « Je n'ai pas testé » |
| Combien de remarques l'IA a-t-elle faites, et combien avez-vous classées « pertinentes ici » ? | obligatoire, texte libre | ex. « 6 remarques, 2 pertinentes » |
| Dépôt 2 — Projet | URL Git public | le projet `ListeDetail` |
| JOURNAL-IA — synthèse du tri | obligatoire, texte libre | 2-3 lignes : la remarque la plus pertinente et pourquoi elle est retenue ; la moins pertinente ICI et pourquoi elle est écartée (hors périmètre du jour, par exemple) |

---

## 8. Repères théoriques — résumé des slides « Navigation »

### Penser en graphe : écrans, routes, chemins

```
ÉCRAN LISTE                                    ÉCRAN DÉTAIL
route : "liste"     -- clic sur un produit -->  route : "detail/{produitId}"
LazyColumn des          navigate("detail/3")    Un produit, ses informations,
produits (séance 4)                             un bouton retour
                     <-- retour ---------------  
                         popBackStack()
```

> **Le parallèle web :** route ↔ URL · argument ↔ paramètre d'URL · retour ↔ bouton précédent du navigateur. La navigation mobile a convergé vers les idées du web — terrain connu.

### Le graphe en code : NavHost et routes

```kotlin
// AppNavigation() — le code exact du projet fourni
val navController = rememberNavController()

NavHost(navController, startDestination = "liste") {

    composable("liste") {
        EcranListe(
            produits = produits,
            onProduitClick = { produitId ->
                // TODO 2 du mini-TP
            }
        )
    }

    // TODO 1 du mini-TP : la route "detail/…"
}
```

**À retenir :**

- `NavHost` : la carte routière — toutes les destinations.
- `composable("route") { ... }` : une route → un écran.
- `navController` : le GPS — `navigate`, `popBackStack`.
- Les écrans ne connaissent pas le `navController` : ils reçoivent des lambdas — l'écran signale, la navigation décide.

### Faire voyager une information : l'argument de route

```kotlin
// (1) DÉCLARER la route avec son argument
composable("detail/{produitId}") { backStackEntry ->

    // (3) RELIRE l'argument à l'arrivée
    val id = backStackEntry.arguments
        ?.getString("produitId")?.toIntOrNull()
    val produit = produits.find { it.id == id }

    if (produit != null) {
        EcranDetail(produit, onRetour = { … })
    }
}

// (2) NAVIGUER en remplissant l'emplacement
navController.navigate("detail/$produitId")
```

**Pourquoi l'identifiant, pas l'objet ?**

- La route est du texte, comme une URL.
- L'écran retrouve la donnée par lui-même (`find` aujourd'hui, requête Room en séance 7).
- `toIntOrNull` + `if != null` : un identifiant invalide n'affiche rien — il ne plante pas.

### Revenir : la backstack — retrouvailles avec la séance 3

| | EMPILE un écran | DÉPILE | DÉPILE aussi — gratuitement |
|---|---|---|---|
| Ce qui se passe | liste → liste + détail. L'écran précédent reste dessous, prêt au retour. | Retour à l'écran précédent — le bouton « Retour à la liste » du détail (TODO 3). | Le geste ou la touche du téléphone fait la même chose, sans une ligne de code. Deux chemins, un comportement. |
| Code | `navigate(…)` | `popBackStack()` | Retour système |

> **Et quand un écran entier est de trop ?** Une confirmation → `AlertDialog` · une information passagère → `Snackbar`. À reconnaître aujourd'hui ; utilisation naturelle le moment venu.

---

## 9. Déroulé de la séance (résumé slides)

| Étape | Contenu |
|---|---|
| 1 — Lire et prédire | Lire `MainActivity.kt` ; prédire : que fait un clic avant les TODO ? que fera le retour SYSTÈME depuis le détail (souvenir de la séance 3) ? |
| 2 — Les trois TODO | TODO 1 : la route `detail/{produitId}` (modèle en commentaire). TODO 2 : `navigate` au clic. TODO 3 : `popBackStack`. Vérifier le bon produit |
| 3 — Observer | Retour système depuis le détail, puis depuis la liste : noter la différence. Vérifier l'affichage du litchi (prix null) |
| 4 — Voie ouverte, trier une revue | Revue IA de `AppNavigation()` ; trier chaque remarque : pertinente / non pertinente ici, avec un mot de justification |

> Dépôt en fin de séance : formulaire « S5 · Dépôt des livrables » — feuille, projet ZIP/URL Git, tri des remarques en 3 lignes.
