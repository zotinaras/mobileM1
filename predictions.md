# Prédictions — Mini-TP 1 (Partie B)

| Appel | Prédiction | Sortie observée | Écart ? | Explication si écart |
|-------|-----------|------------------|---------|------------------------|
| P1    | P1: [Café, Girofle, Litchi, Vanille] | P1: [Café, Girofle, Litchi, Vanille] | Non | Aucun écart : `produitsCollectes` extrait le nom de chaque produit, élimine les doublons avec `distinct()` et trie alphabétiquement avec `sorted()`. |
| P2    | P2: 30.0 kg de Litchi (RASOA Marie) — prix non fixé | P2: 30.0 kg de Litchi (RASOA Marie) — prix non fixé | Non | Aucun écart : `collectes[4]` concerne le litchi dont le prix est `null`. L'expression `?.let { ... } ?: "prix non fixé"` renvoie bien la valeur de repli. |
| P3    | P3: 6 collectes à Ambodivoara | P3: 6 collectes à Ambodivoara | Non | Aucun écart : les producteurs 1 (RAKOTO Jean, 3 collectes) et 3 (RANDRIA Paul, 3 collectes) sont du village Ambodivoara, soit 6 collectes au total. |
| P4    | P4: 1 250 000 Ar | P4: 1 250 000 Ar | Non | Aucun écart : `formatAriary` inverse la partie entière, découpe par paquets de 3 chiffres (`chunked(3)`), insère les espaces puis ré-inverse pour obtenir le formatage des milliers. |
