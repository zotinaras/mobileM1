# JOURNAL-IA — Mini-TP 7

- L'IA a généré un test unitaire pour une requête du DAO Room.

- Remarque la plus pertinente : elle a dit qu'il faudrait vérifier le résultat réel de la requête avec des données connues — pas juste que la fonction ne plante pas, mais aussi que le résultat correspond à ce qu'on attend. C'est pertinent car un test qui ne vérifie que la non-exception ne sert à rien.

- Remarque moins pertinente ICI : l'IA a suggeré d'utiliser une base de données en mémoire pour les tests. C'est hors périmètre du jour — on n'a pas encore appris à configurer les tests Room.

- Le test généré se contente probablement de lancer la fonction et de vérifier qu'elle ne plante pas, sans vérifier le contenu du résultat.
