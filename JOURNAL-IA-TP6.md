# JOURNAL-IA — Mini-TP 6

- L'IA a fait une critique de ProduitsViewModel.kt complet. Elle a propose plusieurs remarques.

- La remarque la plus pertinente : elle a dit qu'il faudrait initialiser les produits depuis une source externe (repository) plutôt que de les passer directement dans le ViewModel. C'est pertinent car en vrai, les données viennent d'une base de données ou d'un réseau, pas d'une variable locale.

- La remarque moins pertinente ICI : l'IA a suggere d'ajouter des tests unitaires et une injection de dépendances. C'est hors périmètre du jour — on n'a pas encore vu les tests ni DI, ça viendra plus tard.

- Le duo _uiState / uiState et le flux unidirectionnel fonctionnent correctement, la critique de l'IA portait surtout sur des améliorations futures pas des corrections nécessaires.
