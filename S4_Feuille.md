# Mini-TP 4 - Jetpack Compose
## Feuille de l'etudiant

---

## Etape 1 - Lire et predire (AVANT de lancer)

On a lu MainActivity.kt. La carte est statique, le log RECOMP est en place, les deux TODO sont en commentaire. On a predit sans lancer.

| Prediction | Votre nombre | Pourquoi (une phrase) |
|---|---|---|
| **P1** — Au premier affichage de l'ecran, combien de lignes RECOMP au Logcat ? | 1 | La carte est affichee une seule fois quand setContent est appelle, donc le log s'affiche une fois. |
| **P2** — Une fois le TODO A fait : combien de NOUVELLES lignes RECOMP apres 3 clics sur « Ajouter 1 kg » ? | 3 | Chaque clic change quantite via mutableStateOf, ce qui declenche une recomposition et donc une ligne RECOMP. |

---

## Etape 2 - TODO A : le compteur de quantite

On a declare en haut de ProduitCard :

```kotlin
var quantite by remember { mutableStateOf(0) }
```

On a remplace le Text et le Button par :

```kotlin
Text("Quantite : $quantite kg")
Button(onClick = { quantite++ }) { Text("Ajouter 1 kg") }
```

**Verification :**
- [x] Au lancement, 1 ligne RECOMP au Logcat (prediction P1 verifiee)
- [ ] 3 clics sur "Ajouter 1 kg" = 3 nouvelles lignes RECOMP

**Resultat observe :** apres 3 clics sur « Ajouter 1 kg », il n'y a PAS de nouvelles lignes RECOMP au Logcat. La prediction P2 n'est pas verifiee dans l'etat actuel.

**Explication de l'ecart :** le state quantite ne declenche pas de recomposition comme prevu. Cela peut etre un probleme de compatibilite entre les versions de Kotlin et Compose dans le projet, ou bien le state n'est pas correctement observe par le systeme de composition.

**Question de control :** si on retire mentalement le remember, que deviendrait le compteur a chaque clic ? Il reviendrait a 0 a chaque recomposition parce que remember est ce qui fait survivre la valeur a travers les recompositions. Sans remember, chaque fois que la fonction est rappelée, quantite est remis a 0.

---

## Etape 3 - TODO B : la carte selectionnable

On a declare :

```kotlin
var selectionnee by remember { mutableStateOf(false) }
```

On a ajoute au Modifier de la Card :
```kotlin
.clickable { selectionnee = !selectionnee }
```

Et on a change la couleur selon l'etat :
```kotlin
colors = CardDefaults.cardColors(
    containerColor = if (selectionnee)
        MaterialTheme.colorScheme.primaryContainer
    else MaterialTheme.colorScheme.surfaceVariant
)
```

**Verification :**
- [ ] Un clic sur la carte change sa couleur
- [ ] Un clic produit une nouvelle ligne RECOMP
- [ ] La capture du Logcat est sauvegardee

**Observation bonus :** quand on tourne l'ecran, la quantite et la selection sont perdues car remember ne survivent pas a la rotation. La solution est le ViewModel (vu en seance 3).

---

## Etape 4 - Voie ouverte : juger une variante de l'IA

On a demande a l'IA de proposer une variante de mise en page de ProduitCard. On a compare les deux versions et on a rendu un jugement.

**Verdict en 3 lignes :** *(a remplir apres avoir discute avec l'IA)*

- ...
- ...
- ...

---

## Logcat observe

**Au lancement :**
1 ligne RECOMP : "ProduitCard se (re)compose"

**Apres 3 clics sur « Ajouter 1 kg » :**
0 nouvelles lignes RECOMP (ecart avec la prediction P2)

---

### Choix de reponse remplis

- Combien de lignes RECOMP au premier affichage ? 1 (exact)
- Combien de NOUVELLES lignes apres 3 clics ? 0 (pas comme prevu — ecart constate)
- La question de control : sans remember le compteur reviendrait a 0 a chaque clic
- Le sélecteur de partage s'ouvre ? *(a remplir)*
