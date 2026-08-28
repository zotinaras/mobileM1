# Predictions - Mini-TP 1 (Partie B)

| Appel | Prediction | Sortie observee | Ecart ? | Explication si ecart |
|-------|-----------|------------------|---------|------------------------|
| P1    | P1: [Cafe, Girofle, Litchi, Vanille] | P1: [Café, Girofle, Litchi, Vanille] | Non | Aucun ecart : la fonction prend le nom de chaque produit, enleve les doublons avec distinct() et fait le tri alphabetique avec sorted(). |
| P2    | P2: 30.0 kg de Litchi (RASOA Marie) — prix non fixé | P2: 30.0 kg de Litchi (RASOA Marie) — prix non fixé | Non | Aucun ecart : pour le litchi le prix est null, donc grace au Elvis ?: on affiche bien le texte par defaut "prix non fixé". |
| P3    | P3: 6 collectes à Ambodivoara | P3: 6 collectes à Ambodivoara | Non | Aucun ecart : RAKOTO Jean (id 1) et RANDRIA Paul (id 3) sont a Ambodivoara, ce qui fait 6 collectes au total sur les 8. |
| P4    | P4: 1 250 000 Ar | P4: 1 250 000 Ar | Non | Aucun ecart : la fonction inverse les chiffres pour faire des paquets de 3 avec chunked(3), met les espaces et reinverse a la fin. |
