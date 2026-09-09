# Mini-TP 7 — Room et l'offline-first — « Trois requêtes »
*ITUniversity — Module M1 · Développement mobile Kotlin — Séance 7*
*Entity, DAO, Flow — travail individuel, sur le projet fourni « ListeDetail v3 »*

> ⚠️ **Le projet source** (`Donnees.kt` avec l'Entity, le DAO et ses trois `TODO`, ainsi que `ProduitsViewModel.kt` avec son bloc « ÉTAPE 3 ») n'est **pas** inclus dans ce récapitulatif : il est fourni séparément dans `MiniTP7_ListeDetailV3.zip` sur le Drive de la séance. Télécharge-le et ouvre-le dans Android Studio.

---

## 1. Objectifs

- Écrire trois requêtes SQL dans un DAO Room : un tri, un filtre paramétré, un agrégat.
- Brancher ces requêtes à l'écran et observer la mise à jour automatique (`Flow`).
- Constater que les données survivent à la fermeture complète de l'application.
- Juger un test généré par l'IA : teste-t-il vraiment quelque chose ?

---

## 2. Règles du mini-TP

1. Les prédictions se remplissent dans le modèle de l'étape 1 **AVANT tout lancement** de l'application.
2. Pendant les étapes 1 à 3, **aucune assistance IA** — complétion IA de l'IDE désactivée.
3. Les écrans, la navigation et le ViewModel sont fournis : le travail est dans `Donnees.kt` (trois `TODO`), puis dans le bloc « ÉTAPE 3 » du ViewModel.
4. Première synchronisation Gradle un peu longue : ce projet ajoute Room et son compilateur (KSP). C'est normal.

---

## 3. Étape 1 — Lire et prédire (sur papier, avant tout lancement)

Télécharger `MiniTP7_ListeDetailV3.zip` depuis le dossier Drive de la séance, le décompresser, ouvrir le projet dans Android Studio (`File → Open`) et **lire** `Donnees.kt` en entier : l'Entity (la table), le DAO (les fonctions fournies et les trois `TODO`), la Database. Puis remplir le modèle :

| Prédiction | Votre réponse |
|---|---|
| **P1** — Au tout premier lancement, avant vos TODO : qu'affichera l'écran (liste, stock total, puces de mode) ? Pourquoi ? | |
| **P2** — Vous fermez COMPLÈTEMENT l'application (pas seulement l'écran), puis vous la rouvrez. Les produits sont-ils encore là ? Et le mode d'affichage choisi ? Justifiez. | |

> Ne rien lancer avant d'avoir rempli les deux lignes du modèle.

---

## 4. Étape 2 — Les trois requêtes (du SQL que vous connaissez)

5. **TODO 1 — TRI** : les produits du plus cher au moins cher, les prix non fixés (`NULL`) EN DERNIER. L'indice SQL est donné dans le fichier :
   ```sql
   ORDER BY prixKg IS NULL, prixKg DESC
   ```
   Question à noter sur la feuille : pourquoi `prixKg IS NULL` en premier critère fait-il descendre les `NULL` en bas ?

6. **TODO 2 — FILTRE** : les produits dont le stock dépasse un seuil PASSÉ EN PARAMÈTRE. Rappel de syntaxe : dans la requête, un paramètre de fonction s'écrit `:nomDuParametre`.

7. **TODO 3 — AGRÉGAT** : le stock total de tous les produits. Le type de retour est `Flow<Double?>` — expliquer en une phrase sur la feuille pourquoi le résultat peut être `null`.

> Après chaque TODO, compiler : Room vérifie le SQL à la COMPILATION. Une erreur de nom de colonne ne passera pas — c'est le premier correcteur.

---

## 5. Étape 3 — Brancher et observer

8. Ouvrir `ProduitsViewModel.kt` et suivre le bloc commenté « ÉTAPE 3 » : brancher AU MOINS un mode d'affichage à la requête, et le stock total à l'agrégat.
9. Lancer : les puces en haut de l'écran doivent changer la liste, et le stock total s'afficher. Capturer l'écran pour chaque mode branché.

**Observation demandée** (une phrase sur la feuille) : dans le tri par prix décroissant, où se place le litchi (prix non fixé) — et quelle partie de la requête l'a décidé ?

---

## 6. Voie ouverte — une tâche IA unique : juger un test

10. Demander à l'IA de votre choix un test pour UNE des trois requêtes. Prompt suggéré :

    > « Voici une fonction de DAO Room. Écris un test unitaire pour cette requête. » *(coller votre fonction)*

11. Il n'y a PAS à exécuter ce test : c'est un exercice de lecture critique. Répondre en trois lignes : que vérifie RÉELLEMENT ce test ? Se contente-t-il de constater que la fonction ne plante pas ? Suppose-t-il des données qui n'existent pas dans notre base ? Que faudrait-il vérifier en plus pour qu'il ait de la valeur ? Recopier ces trois lignes dans le champ « JOURNAL-IA » du formulaire.

---

## 7. Livrables (formulaire « S7 · Dépôt des livrables »)

Tout se dépose en fin de séance dans le formulaire unique « S7 · Dépôt des livrables » — le lien est affiché en séance et dans le dossier Drive de la séance :

- cette feuille remplie (prédictions, questions SQL, observation du litchi), en photo ou PDF ;
- les captures d'écran des modes d'affichage branchés (avec le stock total visible) ;
- le projet avec les trois requêtes, en URL Git ;
- le jugement en trois lignes sur le test généré, recopié dans le champ « JOURNAL-IA » du formulaire.

Ces dépôts servent au suivi de votre progression.

### Contenu exact du formulaire de dépôt

| Champ | Nature | Précision |
|---|---|---|
| Adresse e-mail | obligatoire | |
| Numéro ETU | obligatoire | ex. `ETU000123` |
| Dépôt 1 — la feuille (prédictions, questions SQL, observation du litchi) | lien Drive public | photo ou PDF |
| Requêtes qui compilent et fonctionnent | obligatoire, choix multiple (cocher celles abouties, l'honnêteté prime) | « TODO 1 — tri par prix décroissant, NULL en dernier » / « TODO 2 — filtre par seuil de stock (paramètre) » / « TODO 3 — agrégat SUM du stock total » |
| Valeur du stock total affichée à l'écran (en kg) | obligatoire, texte libre | le nombre montré par l'application (indice : la somme des stocks du jeu de données initial) |
| Dans le tri par prix décroissant, où se place le litchi (prix non fixé) ? | obligatoire, choix unique | « En dernier, après tous les produits qui ont un prix » / « En premier » / « Au milieu, à une place imprévisible » / « Je n'ai pas branché ce mode » |
| Dépôt 2 — Captures des modes d'affichage | lien Drive public | une capture par mode branché, stock total visible ; PDF + Image |
| Dépôt 3 — Projet | lien GitHub public | le projet `ListeDetail v3` |
| JOURNAL-IA — jugement sur le test généré | obligatoire, texte libre | 3 lignes : que vérifie RÉELLEMENT ce test ? Se contente-t-il de constater que la fonction ne plante pas ? Suppose-t-il des données qui n'existent pas ? Que faudrait-il vérifier en plus ? |

---

## 8. Repères théoriques — résumé des slides « Room et l'offline-first »

### Offline-first : la base d'abord, le réseau ensuite

| Modèle naïf : le réseau d'abord | Offline-first : la base d'abord |
|---|---|
| L'écran demande au réseau et affiche la réponse | L'écran lit TOUJOURS la base locale |
| Pas de réseau → pas d'application | Le réseau alimente la base quand il est là |
| La moindre coupure devient une panne | L'application fonctionne toujours |
| Le modèle par défaut des applications conçues en couverture continue | La coupure n'est plus une panne : c'est un retard de synchronisation |

> « La base locale n'est pas un cache : c'est la source de vérité. Le réseau ne fait que la nourrir. »
> Le contexte malgache rend ce choix évident : connectivité inégale, coupures fréquentes, coût des données.

### Room en trois éléments — et c'est tout

1. **`@Entity` — une table.** Une data class = une table. Chaque propriété = une colonne. `@PrimaryKey` = la clé. Un `Double?` devient une colonne NULL autorisée.
2. **`@Dao` — les requêtes.** Une interface : chaque fonction porte son SQL en annotation. Le SQL est VÉRIFIÉ À LA COMPILATION — une faute de colonne ne compile pas.
3. **`@Database` — l'assemblage.** Liste les entités, expose les DAO. Le builder crée le fichier de base sur le téléphone. Le compilateur génère tout le reste.

> Le SQL que vous connaissez déjà — simplement posé dans des annotations, et vérifié par le compilateur.

### Le DAO : Flow ou suspend — un choix qui a du sens

```kotlin
@Dao
interface ProduitDao {

    // Un ROBINET : ré-émet à chaque changement
    @Query("SELECT * FROM produits ORDER BY nom ASC")
    fun tousLesProduits(): Flow<List<Produit>>

    // Une question PONCTUELLE : suspend
    @Query("SELECT * FROM produits WHERE id = :id")
    suspend fun parId(id: Int): Produit?

    @Insert
    suspend fun insererTous(p: List<Produit>)
}
```

**Le critère de choix :**
- « Je veux être tenu au courant » → `Flow` : l'écran se met à jour tout seul.
- « Je pose une question ponctuelle » → `suspend`.
- `suspend`, parce qu'une lecture disque ne doit pas bloquer l'interface (séance 2).
- Room refuse de compiler une requête bloquante sur le thread principal.

**Et pour ÉCRIRE dans la base ?** Quatre annotations, toutes `suspend` :

| `@Insert` | `@Update` | `@Delete` | `@Query("DELETE …")` |
|---|---|---|---|
| ajoute une ligne | modifie — par la clé primaire de l'objet | supprime — par la clé primaire de l'objet | agir sur un CRITÈRE, pas sur un objet |

> Le `Flow` de la séance 2 prend enfin tout son sens : la base émet, le ViewModel transforme, l'écran se recompose.

### Démonstration : la coupure n'est pas une panne

1. **Réseau activé** — la collecte apparaît immédiatement, puis passe en « synchronisée ». Le serveur la reçoit. → L'utilisateur n'a attendu que la base : zéro seconde de réseau.
2. **Réseau coupé** — deux ou trois collectes enregistrées : elles s'affichent aussitôt, marquées « en attente ». → L'application fonctionne entièrement. Rien n'est bloqué.
3. **La preuve** — fermer l'application, la rouvrir : les collectes sont toujours là. → Les données sont ACQUISES, en base. Elles ne peuvent plus être perdues.
4. **Le réseau revient** — « Synchroniser » : la file se vide, les marques disparaissent, le serveur se remplit. → La coupure n'était pas une panne : c'était un retard.

> Le réseau est simulé par un interrupteur dans l'application (faux serveur en mémoire) : la démonstration reste reproductible, sans dépendre de la connexion de la salle.

### Les lignes qui font toute la différence

**Le geste central : la base d'abord**

```kotlin
fun enregistrerCollecte(…) {
    viewModelScope.launch {
        val c = Collecte(…, synchronisee = false)

        dao.inserer(c)   // 1. LA BASE
        synchroniser()   // 2. le réseau
    }
}
```

> Inverser ces deux lignes — envoyer d'abord, écrire ensuite — donnerait le modèle naïf : celui qui perd la donnée quand le réseau manque.

**La boucle de synchronisation**

```kotlin
for (c in dao.enAttente()) {
    val recue = serveur.envoyer(c)
    if (recue)
        dao.modifier(c.copy(synchronisee = true))
    else break   // on réessaiera
}
```

> Le drapeau `synchronisee` distingue « acquis localement » de « remonté au serveur ». Un booléen suffit — et `copy()` de la séance 1 sert encore ici.

**Dans une vraie application :** le faux serveur devient une interface Retrofit · le bouton « Synchroniser » devient `WorkManager`, qui programme la reprise et survit à la fermeture. Le reste — l'ordre base-puis-réseau, le drapeau, la boucle — ne change pas.

### Les préférences : quand une base serait disproportionnée

**Une préférence, c'est…** le mode d'affichage choisi, le nom du collecteur connecté, la date de dernière synchronisation, le seuil de stock, le thème sombre.

> Point commun : une valeur unique, sans structure, qu'on relit au démarrage — et qu'on n'INTERROGE jamais.

**`DataStore` — trois idées, et c'est tout :**

```kotlin
// 1. LA CLÉ : un nom et un type
val MODE = stringPreferencesKey("mode_affichage")

// 2. LIRE : un Flow — l'écran suit tout seul
val mode: Flow<String> = dataStore.data
    .map { prefs -> prefs[MODE] ?: "nom" }

// 3. ÉCRIRE : suspend — jamais sur le thread UI
suspend fun changerMode(m: String) {
    dataStore.edit { prefs -> prefs[MODE] = m }
}
```

| | Room | DataStore |
|---|---|---|
| **Pour quoi** | Des enregistrements : produits, collectes | Des réglages : un mode, un nom, une date |
| **On interroge ?** | Oui — trier, filtrer, agréger, joindre | Non — on lit une clé, on écrit une clé |
| **Forme** | Tables, SQL vérifié à la compilation | Clés typées, aucune structure |
| **Lecture** | `Flow` ou `suspend`, selon l'usage | `Flow` — même réflexe |

> `DataStore` remplace `SharedPreferences`, qui écrivait sur le thread de l'interface.
> Le critère : on veut interroger → Room · on veut retenir un réglage → DataStore.

---

## 9. Déroulé de la séance (résumé slides)

| Étape | Contenu |
|---|---|
| 1 — Lire et prédire | Lire `Donnees.kt` ; prédire : qu'affiche l'écran avant les TODO ? et après fermeture complète puis réouverture, les données sont-elles là ? |
| 2 — Les trois requêtes | TODO 1 : tri par prix décroissant, prix nuls en dernier. TODO 2 : filtre par seuil de stock (paramètre). TODO 3 : SUM du stock total → `Flow<Double?>` |
| 3 — Brancher et observer | Suivre le bloc ÉTAPE 3 du ViewModel : brancher au moins un mode + le stock total. Observer : où se place le litchi dans le tri par prix, et pourquoi ? |
| 4 — Voie ouverte, juger un test | Faire générer un test d'une requête par l'IA ; en 3 lignes : que teste-t-il RÉELLEMENT, et que faudrait-il vérifier en plus ? |

> Vos requêtes rendent des `Flow` : changez une donnée, l'écran se met à jour tout seul. C'est la boucle complète du module.
