package com.example.tfg_appbanca.ui.screens.pantallaInicio


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PantallaInicioViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PantallaInicioUiState())
    val uiState: StateFlow<PantallaInicioUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        // In a real app, this would fetch data from repositories
        val newState = PantallaInicioUiState(
            ingresos = listOf(70f, 60f, 80f, 65f, 50f, 45f, 55f, 68f, 62f, 70f, 60f, 75f),
            gastos = listOf(50f, 40f, 60f, 50f, 55f, 50f, 48f, 60f, 59f, 63f, 50f, 58f),
            meses = listOf("E", "F", "M", "A", "M", "J", "J", "A", "S", "O", "N", "D"),
            balanceTotal = 103.00f,
            porcentajeCambio = 10.1f,
            recentTransactions = List(10) { index ->
                Transaction(
                    id = index,
                    reason = when (index % 3) {
                        0 -> "Compra en supermercado"
                        1 -> "Pago de factura"
                        else -> "Transferencia recibida"
                    },
                    place = when (index % 3) {
                        0 -> "Mercadona"
                        1 -> "Compañía eléctrica"
                        else -> "Juan Pérez"
                    },
                    amount = (index * 12.34f),
                    isPositive = index % 3 == 2
                )
            },
            contacts = List(10) { index -> "C${index + 1}" }
        )
        _uiState.value = newState
    }

    fun onQuickActionClicked(action: QuickAction) {
        when (action) {
            QuickAction.BIZUM -> { /* Handle bizum action */ }
            QuickAction.TRANSFER -> { /* Handle transfer action */ }
        }
    }

    fun onContactClicked(contactInitials: String) {
        // Handle contact click
    }
}

data class PantallaInicioUiState(
    val ingresos: List<Float> = emptyList(),
    val gastos: List<Float> = emptyList(),
    val meses: List<String> = emptyList(),
    val balanceTotal: Float = 0f,
    val porcentajeCambio: Float = 0f,
    val recentTransactions: List<Transaction> = emptyList(),
    val contacts: List<String> = emptyList()
)

enum class QuickAction {
    BIZUM, TRANSFER
}

data class Transaction(
    val id: Int,
    val reason: String,
    val place: String,
    val amount: Float,
    val isPositive: Boolean
)