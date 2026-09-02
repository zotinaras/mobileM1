# Tableau des écarts — Mini-TP 2 — Coroutines

| Programme | Écart constaté | Explication en une phrase |
|---|---|---|
| **Programme 1** | B s'affiche après C au lieu de s'afficher entre A et C | `launch` lance une coroutine en arrière-plan : le programme ne l'attend pas, donc C s'affiche avant que B ne soit imprimé. |
| **Programme 2** | Durée mesurée ~1595 ms au lieu de ~2500 ms si on pensait séquentiel | Les deux `async` démarrent en même temps, donc la durée totale correspond au maximum des deux délais, pas à leur somme. |
| **Programme 3** | Durée mesurée ~1863 ms au lieu de ~1000 ms attendu pour du concurrent | Chaque `async` est suivi immédiatement par son `.await()`, donc le deuxième ne démarre qu'après la fin du premier — c'est séquentiel malgré l'utilisation de `async`. |


2580

1040

- Ecart choisi   : Programme 3 - predit un comportement concurrent, mais le resultat est sequentiel. La duree mesuree (1863 ms) est bien plus longue que ce qui etait attendu (1000 ms).

- Explication  :
  Chaque 'async' est suivi de son '.await()' sur la meme ligne, donc le deuxieme
  ne demarre qu'apres la fin du premier. Les taches s'executent l'une apres
  l'autre. Il faut decaler le '.await()' pour que les deux tournent en meme temps
