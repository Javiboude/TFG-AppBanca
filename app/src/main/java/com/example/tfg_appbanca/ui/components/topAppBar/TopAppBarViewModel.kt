package com.example.tfg_appbanca.ui.components.topAppBar

import androidx.lifecycle.ViewModel
import com.example.tfg_appbanca.data.model.gets.datosUsuario
import com.example.tfg_appbanca.data.repositories.GetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TopAppBarViewModel @Inject constructor(
    private val repository: GetRepository
) : ViewModel() {
    private val _usuario = MutableStateFlow<datosUsuario?>(null)
    val usuario: StateFlow<datosUsuario?> = _usuario

    private val _reloadUser = MutableStateFlow(false)
    val reloadUser: StateFlow<Boolean> = _reloadUser.asStateFlow()

    suspend fun getUsuarioInfo(numeroTelefono: String) {
        val user = repository.getInfoPersonajeByNumeroTelefono(numeroTelefono)
            _usuario.value = user
    }
}


