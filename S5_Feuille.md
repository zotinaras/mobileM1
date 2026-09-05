# Mini-TP 5 - Navigation
## Feuille de l'etudiant

---

## Etape 1 - Lire et predire (AVANT de lancer)

J'ai lu MainActivity.kt. Les deux écrans sont fournis et fonctionnels, mais la navigation n'est pas encore faite. Il y a 3 TODO dans AppNavigation(). J'ai predit sans lancer.

| Prediction | Votre reponse |
|---|---|
| **P1** — Au lancement (AVANT tout TODO) : que fait un clic sur un produit de la liste, et pourquoi ? | Rien. En cliquant sur un produit, la fonction onProduitClick est appelee mais elle est vide (commentaire TODO 2 seulement). Le produit est envoyé au clic mais rien ne se passe car il n'y a pas de navigation. |
| **P2** — Une fois les TODO faits : que fera le bouton retour SYSTÈME depuis l'écran de détail ? Et depuis la liste ? | Depuis le détail, le retour SYSTÈME dépile l'écran de détail et revient à la liste (la liste est restée dans la pile depuis le navigate). Depuis la liste, le retour SYSTÈME ferme l'application car la pile est vide — il n'y a plus d'écran en dessous. |

---

## Etape 2 - Les trois TODO

**TODO 1** — la route du détail :
J'ai déclaré `composable("detail/{produitId}")` avec l'argument produitId. L'argument est lu avec `backStackEntry.arguments?.getString("produitId")?.toIntOrNull()`, le produit est retrouvé avec `produits.find { it.id == id }`, et si trouvé on affiche `EcranDetail`. Le null est géré avec `if (produit != null)`.

**TODO 2** — naviguer au clic :
J'ai ajouté `navController.navigate("detail/$produitId")` dans `onProduitClick`. Une seule ligne.

**TODO 3** — le retour :
J'ai ajouté `navController.popBackStack()` dans `onRetour`. Une seule ligne.

**Verification du circuit complet :**
- [x] Liste → clic sur « Girofle » → le détail affiche bien le Girofle (pas un autre !)
- [x] Le bouton « Retour à la liste » fonctionne
- [x] Le retour système depuis le détail revient à la liste
- [ ] Le retour système depuis la liste ferme l'application

---

## Etape 3 - Observer la backstack

**Depuis l'écran de détail, retour SYSTÈME :**
L'application revient bien à la liste. Le détail a été dépilé. La prédiction P2 est vérifiée.

**Depuis la liste, retour SYSTÈME :**
L'application se ferme. La pile était vide — la liste est le seul écran restant. Différence avec la rotation (séance 3) : là, on quitte l'application, alors que la rotation détruisait et recréait le même écran.

**Cas limite — litchi (prix non fixé) :**
L'écran détail affiche bien "Prix non fixé" quand on clique sur le litchi. C'est la construction `?.let { ... } ?: "..."` de la séance 1 qui rend cela possible — le null est géré proprement sans planter.

---

## Etape 4 - Voie ouverte : trier une revue IA

J'ai soumis la fonction AppNavigation() complète à l'IA pour une revue. J'ai trié chaque remarque.

**Synthèse du tri en 3 lignes :**

- Remarque la plus pertinente : l'IA a signalé qu'il faudrait gérer le cas où le produit n'est pas trouvé (id invalide) — c'est pertinent car ça pourrait arriver si l'URL est modifiée manuellement.
- Remarque moins pertinente : l'IA a suggéré d'ajouter un ViewModel et des routes typées — hors périmètre du jour, on apprendra ça en séance 6 et 7.
- Le circuit de navigation fonctionne correctement avec les trois TODO, les remarques de l'IA étaient plutôt des améliorations futures que des corrections nécessaires.

---

### Commentaires supplementaires

La navigation est maintenant fonctionnelle. Le circuit complet marche : liste → clic → détail → retour à la liste. Le retour système se comporte comme prévu. Le litchi s'affiche correctement avec son prix non fixé grâce au `?.let ?:`.

Le lien avec la séance 3 est clair : le retour système fonctionne comme `popBackStack()`, et la backstack gère les écrans comme une pile.
