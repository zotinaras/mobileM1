 
- Ecart choisi   : Programme 3 - predit un comportement concurrent, mais le resultat est sequentiel. La duree mesuree (  1863 ms) est bien plus longue que ce qui etait attendu ( 1000 ms).

- Explication  :
  Chaque 'async' est suivi de son '.await()' sur la meme ligne, donc le deuxieme
  ne demarre qu'apres la fin du premier. Les taches s'executent l'une apres
  l'autre. Il faut decaler le '.await()' pour que les deux tournent en meme temps
