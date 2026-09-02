# Mini-TP 3 — Anatomie Android — « Observer le cycle de vie »
*ITUniversity — Module M1 · Développement mobile Kotlin — Séance 3*
*Cycle de vie, Intents, Logcat — travail individuel, sur l'application fournie « CycleDeVie »*

> ⚠️ **Le projet source** (`MainActivity.kt`, `activity_main.xml`, etc.) n'est **pas** inclus dans ce récapitulatif : il est fourni séparément dans `MiniTP3_CycleDeVie.zip` sur le Drive de la séance. Télécharge-le et ouvre-le dans Android Studio.

---

## 1. Objectifs

- Prédire la séquence exacte des callbacks du cycle de vie pour deux scénarios, puis la vérifier au Logcat.
- Observer qu'à la rotation, l'Activity est détruite puis recréée — et noter ce que cela détruit.
- Compléter un Intent implicite de partage.
- Juger le diagnostic d'une stack trace proposé par l'IA.

---

## 2. Règles du mini-TP

1. Les prédictions se remplissent dans le modèle de l'étape 1 **AVANT tout lancement** de l'application : lire, prédire, puis seulement exécuter — c'est l'ordre des étapes qui fait tout l'intérêt du mini-TP.
2. Pendant les étapes 1 à 3, **aucune assistance IA** — complétion IA de l'IDE désactivée.
3. Émulateur lent ? Des appareils physiques de prêt sont disponibles — demander.

---

## 3. Étape 1 — Lire et prédire (sur papier, avant tout lancement)

Télécharger `MiniTP3_CycleDeVie.zip` depuis le dossier Drive de la séance, le décompresser, ouvrir le projet dans Android Studio (`File → Open`) et **lire** `MainActivity.kt` — sans lancer l'application. Puis, pour chacun des deux scénarios, remplir le modèle ci-dessous avec la séquence exacte et complète des callbacks (parmi : `onCreate`, `onStart`, `onResume`, `onPause`, `onStop`, `onRestart`, `onDestroy`) :

- **Scénario A** : tourner l'écran (rotation portrait → paysage) ;
- **Scénario B** : appuyer sur le bouton accueil, puis revenir à l'application.

| Scénario | Séquence EXACTE prédite (dans l'ordre) | Réponse à la question « + » (une phrase) |
|---|---|---|
| **A — Rotation de l'écran** + que devient un compteur stocké dans l'Activity ? | | |
| **B — Accueil, puis retour** + quelle différence essentielle avec la rotation ? | | |

> Ne rien lancer avant d'avoir rempli les deux lignes du modèle.

---

## 4. Étape 2 — Observer au Logcat

4. Lancer maintenant l'application sur émulateur ou appareil (laisser Gradle finir sa synchronisation à la première ouverture).
5. Dans le Logcat, filtrer avec : `tag:CYCLE`
6. Jouer les deux scénarios et remplir le tableau :

| Scénario | Séquence observée | Écart avec ma prédiction + explication |
|---|---|---|
| **Rotation de l'écran** | | |
| **Accueil, puis retour** | | |

**Question d'observation** (à répondre en une phrase sur la feuille) : à la rotation, le numéro d'instance affiché par `onCreate` change. Qu'est-ce que cela prouve — et qu'arriverait-il à un compteur stocké dans l'Activity ?

**Bonus :** ouvrir le second écran (bouton) et observer l'entrelacement des étiquettes `CYCLE` et `CYCLE-2` : qui se met en pause avant que qui ne se crée ?

---

## 5. Étape 3 — Compléter le partage

Dans `MainActivity`, la fonction `partagerCollecte()` contient un `TODO` : la compléter avec un **Intent implicite `ACTION_SEND`** (type `"text/plain"`, texte `"Collecte du jour : 4,5 kg de vanille"`), lancé via `Intent.createChooser`. Le modèle exact est sur la diapositive « Les Intents » du cours (voir §9 ci-dessous). Vérifier que le sélecteur de partage s'ouvre.

---

## 6. Voie ouverte — une tâche IA unique : juger un diagnostic

Une variante de l'application (que vous n'avez pas) plante au démarrage avec la stack trace ci-dessous. La ligne 29 de sa `MainActivity` est :

```kotlin
findViewById<Button>(R.id.btnPartage).setOnClickListener { partagerCollecte() }
```

La stack trace :

```
java.lang.RuntimeException: Unable to start activity
ComponentInfo{mg.itu.cycledevie/mg.itu.cycledevie.MainActivity}:
java.lang.NullPointerException: findViewById(R.id.btnPartage)
must not be null
    at android.app.ActivityThread.performLaunchActivity(...)
    at android.app.ActivityThread.handleLaunchActivity(...)
    at android.os.Handler.dispatchMessage(Handler.java:106)
    at android.app.ActivityThread.main(ActivityThread.java:8177)
Caused by: java.lang.NullPointerException:
findViewById(R.id.btnPartage) must not be null
    at mg.itu.cycledevie.MainActivity.onCreate(MainActivity.kt:29)
    at android.app.Activity.performCreate(Activity.java:8342)
    ... 11 more
```

7. Soumettre cette stack trace (et la ligne 29) à l'IA de votre choix, en lui demandant : « Diagnostique ce crash : quelle ligne, quelle cause, quelle correction ? »
8. Puis **juger** son diagnostic en trois lignes : désigne-t-il la bonne ligne (celle de NOTRE paquet) ? La bonne cause ? La correction proposée est-elle la bonne ? Comparer avec le vrai layout du projet sous les yeux (`activity_main.xml`) — un indice s'y trouve. Recopier ce verdict dans le champ « JOURNAL-IA » du formulaire de dépôt.

---

## 7. Livrables (formulaire « S3 · Dépôt des livrables »)

Tout se dépose en fin de séance dans le formulaire unique « S3 · Dépôt des livrables » — le lien est affiché en séance et dans le dossier Drive de la séance :

- cette feuille remplie (modèle de prédictions, tableau d'observation, question et bonus), en photo ou PDF ;
- les captures du Logcat filtré (une par scénario), annotées ;
- le projet avec le partage fonctionnel, en lien GIT public ;
- le verdict en trois lignes sur le diagnostic de l'IA, recopié directement dans le champ « JOURNAL-IA » du formulaire.

---

## 8. Repères théoriques — résumé des slides « Anatomie d'une application Android »

### Les quatre composants — les portes d'entrée de l'application

1. **Activity** — un écran. L'utilisateur la voit et la touche.
2. **Service** — un travail sans écran, qui continue en arrière-plan (synchroniser, jouer de la musique).
3. **Broadcast Receiver** — réagir à un événement du système (le réseau revient, la batterie est faible).
4. **Content Provider** — exposer des données aux autres applications (contacts, photos).

> Tous déclarés dans le manifest — la carte d'identité de l'application. Séance 3 : focus sur l'Activity.

### Le cycle de vie de l'Activity — piloté par le système

```
onCreate → onStart → onResume → onPause → onStop → onDestroy
                ↑___________________|
           (onRestart → onStart : l'écran stoppé redevient visible)
```

- **onCreate** — l'écran se construit
- **onStart** — devient visible
- **onResume** — premier plan, interactif
- **onPause** — perd le premier plan
- **onStop** — plus visible
- **onDestroy** — instance détruite

> Les deux phrases à retenir : vous n'appelez JAMAIS ces fonctions — le système les appelle, vous les redéfinissez. Et toute ressource prise dans `onResume` se libère dans `onPause`.

### Le cas qui surprend tout le monde : tourner l'écran

- **Ce qui se passe vraiment** : le système DÉTRUIT l'Activity et en RECRÉE une neuve : `onPause → onStop → onDestroy`, puis `onCreate → onStart → onResume`.
- **La conséquence** : tout ce qui vivait dans l'Activity disparaît — compteur, saisie, liste chargée. Le bug classique : « mon écran se vide quand je tourne le téléphone ».
- **L'ouverture (séance 6)** : il existe un objet qui SURVIT à cette recréation, le `ViewModel`. Aujourd'hui, on observe le problème au Logcat — la solution viendra en séance 6.

### Les Intents : demander un écran — le sien ou celui d'un autre

**Explicite — « ouvre CET écran »** (navigation interne, on nomme la classe visée) :

```kotlin
val intent = Intent(this, SecondActivity::class.java)
startActivity(intent)
```

**Implicite — « trouve qui sait faire »** (le système propose les applis capables : le sélecteur de partage) :

```kotlin
val intent = Intent(Intent.ACTION_SEND)
    .setType("text/plain")
    .putExtra(Intent.EXTRA_TEXT, "Collecte du jour : 4,5 kg")
startActivity(Intent.createChooser(intent, null))
```

> La backstack, en une phrase : chaque écran ouvert s'empile ; le bouton retour dépile. C'est pour cela que « retour » ramène toujours à l'écran précédent.

### L'outillage du jour : Logcat, points d'arrêt, stack trace

1. **Logcat — le journal de bord** : tout ce que le téléphone raconte. Le réflexe : FILTRER — dans le mini-TP, l'étiquette `CYCLE` ne montre que nos callbacks.
2. **Points d'arrêt — figer le temps** : un clic dans la marge, le mode débogage, et le programme se fige à la ligne — variables inspectées, exécution pas à pas.
3. **Stack trace — lire un crash** : chercher la PREMIÈRE ligne qui mentionne notre paquet — c'est presque toujours là. Au-dessus : le mécanisme, pas la cause.

> Trois outils installés aujourd'hui, réutilisés à chaque séance du module.

---

## 9. Déroulé de la séance (résumé slides)

| Étape | Contenu |
|---|---|
| 1 — Prédire | Test « S3 · Prédictions » : la séquence exacte des callbacks à la rotation, puis au passage en arrière-plan et retour. Le projet se déverrouille après soumission |
| 2 — Observer au Logcat | Lancer l'app, filtrer sur `CYCLE`, jouer les deux scénarios, comparer aux prédictions — écarts expliqués par écrit |
| 3 — Compléter le partage | Le bouton « Partager » est vide : y mettre l'Intent implicite vu en cours (`ACTION_SEND`) |
| 4 — Voie ouverte, juger l'IA | Une stack trace fournie ; l'IA propose un diagnostic ; jugement : bonne ligne ? bonne cause ? Verdict en 3 lignes au journal |

> Vous allez VOIR l'Activity mourir et renaître à la rotation — retenez ce que ça détruit : la séance 6 y répond.
