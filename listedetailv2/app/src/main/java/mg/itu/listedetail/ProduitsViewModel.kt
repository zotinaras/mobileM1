package mg.itu.listedetail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

/**
 * Mini-TP 6 — « Compléter la couche manquante »
 *
 * Ce ViewModel est la couche manquante de l'application : il PORTE l'état.
 * Contrairement au remember de la séance 4, il SURVIT à la rotation.
 * Les deux TODO sont complétés.
 */

/** L'état complet de l'interface, en une seule donnée immuable. */
data class EtatUi(
    val produits: List<Produit> = emptyList(),
    val poidsPanierKg: Int = 0,
)

class ProduitsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(EtatUi(produits = produits))
    val uiState: StateFlow<EtatUi> = _uiState

    /** Appelée par l'écran de détail quand l'utilisateur ajoute au panier. */
    fun ajouterAuPanier(poidsKg: Int) {
        _uiState.update { etat ->
            etat.copy(poidsPanierKg = etat.poidsPanierKg + poidsKg)
        }
    }
}
