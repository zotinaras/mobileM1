# Journal IA — Mini-TP 1 — zotina

- Fonction soumise : `collectesValorisables(liste: List<Collecte>)`
- Remarque principale de l'IA : Le code est clair et simple a lire avec filter et sortedByDescending, mais appeler prixEstime(it) plusieurs fois (dans le filtre puis pendant le tri) recalcule plusieurs fois le meme prix au lieu de stocker la valeur.
- Mon verdict (accepte / rejette / nuance) et pourquoi : Nuance - J accepte cette solution pour le TP car elle est tres facile a comprendre et concise pour un debutant, mais je nuance car sur une vraie application avec beaucoup de donnees recalculer les prix pendant le tri ralentirait l execution.
