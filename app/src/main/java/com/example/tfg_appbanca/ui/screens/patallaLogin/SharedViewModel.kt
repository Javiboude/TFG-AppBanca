package com.example.tfg_appbanca.ui.screens.patallaLogin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {

    private val _numeroTelefono = MutableStateFlow<String>("")
    val numeroTelefono: StateFlow<String> = _numeroTelefono.asStateFlow()

    fun setNumeroTelefono(numero: String) {
        viewModelScope.launch {
            _numeroTelefono.value = numero
        }
    }
}