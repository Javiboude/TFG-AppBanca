package com.example.httpclienttest.ui.screens.pantallaDeCarga

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PantallaDeCargaViewModel : ViewModel() {

    // Estado de carga
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    init {
        startLoading()
    }

    private fun startLoading() {
        viewModelScope.launch {
            // Simular una operación de carga
            delay(3000) // 3 segundos de carga
            _isLoading.value = false // Finalizar carga
        }
    }
}