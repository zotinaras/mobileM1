# Journal IA — Mini-TP 1 — zotina

- Fonction soumise : `collectesValorisables(liste: List<Collecte>)`
- Remarque principale de l'IA : L'implémentation est idiomatique et très lisible grâce au chaînage déclaratif (`filter` + `sortedByDescending`), mais le calcul `prixEstime(it)` est exécuté de manière redondante lors du filtrage puis répété à chaque comparaison durant le tri au lieu d'associer temporairement chaque collecte à son prix calculé.
- Mon verdict (accepte / rejette / nuance) et pourquoi : **Nuance** — J'accepte cette solution pour le TP car elle privilégie une grande clarté et concision sur des petits jeux de données, mais je nuance pour la production où un calcul redondant lors d'un tri sur une grande collection dégraderait les performances.
