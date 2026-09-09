package mg.itu.listedetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * Mini-TP 7 — le ViewModel, branché sur la base Room.
 * Les trois requêtes sont implémentées dans Donnees.kt.
 * L'état vient de la BASE via des Flow qui ré-émettent à chaque changement.
 */

enum class ModeAffichage { NOM, PRIX_DECROISSANT, STOCK_SUFFISANT }

data class EtatUi(
    val produits: List<Produit> = emptyList(),
    val mode: ModeAffichage = ModeAffichage.NOM,
    val stockTotal: Double? = null,
)

class ProduitsViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.obtenir(application).produitDao()
    private val mode = MutableStateFlow(ModeAffichage.NOM)

    init {
        // Premier lancement : on remplit la base si elle est vide.
        viewModelScope.launch {
            if (dao.parId(1) == null) dao.insererTous(produitsInitiaux)
        }
    }

    /**
     * L'état de l'écran = les produits (selon le mode choisi) + le stock total.
     * combine() fusionne plusieurs Flow en un seul : dès que l'un ré-émet,
     * l'état est recalculé et l'écran se recompose.
     */
    val uiState: StateFlow<EtatUi> =
        combine(
            dao.tousLesProduits(),
            dao.parPrixDecroissant(),
            dao.stockSuperieurA(10.0),
            dao.stockTotal(),
            mode,
        ) { parNom, parPrix, stockOk, total, modeCourant ->
            val liste = when (modeCourant) {
                ModeAffichage.NOM -> parNom
                ModeAffichage.PRIX_DECROISSANT -> parPrix
                ModeAffichage.STOCK_SUFFISANT -> stockOk
            }
            EtatUi(produits = liste, mode = modeCourant, stockTotal = total)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = EtatUi(),
        )

    fun changerMode(nouveau: ModeAffichage) {
        mode.value = nouveau
    }
}
