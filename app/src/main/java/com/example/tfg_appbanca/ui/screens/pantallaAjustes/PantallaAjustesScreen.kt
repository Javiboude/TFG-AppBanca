package com.example.tfg_appbanca.ui.screens.pantallaAjustes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.httpclienttest.ui.navigation.Destinations
import com.example.tfg_appbanca.R
import com.example.tfg_appbanca.ui.screens.patallaLogin.SharedViewModel

@Composable
fun PantallaAjustesScreen(
    navController: NavController,
    pantallaAjustesViewModel: PantallaAjustesViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel = hiltViewModel()

) {

    val usuario by pantallaAjustesViewModel.usuario.collectAsStateWithLifecycle()
    val numeroTelefono by sharedViewModel.numeroTelefono.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        pantallaAjustesViewModel.getUsuarioInfo(numeroTelefono)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(bottom = 32.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.person),
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(40.dp)
                    .padding(bottom = 8.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            if (usuario != null) {
                Text(
                    text = usuario!!.nombre,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SettingItem(
                icon = R.drawable.money,
                text = "Añadir dinero",
                onClick =  {navController.navigate("${Destinations.PANTALLA_AÑADIR_DINERO}") },
            )

            SettingItem(
                icon = R.drawable.lock,
                text = "Datos personales",
                onClick = {navController.navigate("${Destinations.PANTALLA_DATOS_PERSONALES}") },
            )

            SettingItem(
                icon = R.drawable.out,
                text = "Cerrar sesión",
                textColor = Color.Red,
                onClick = {navController.navigate("${Destinations.PANTALLA_DE_CARGA_URL}") },
            )
        }
    }
}

@Composable
fun SettingItem(
    icon: Int,
    text: String,
    textColor: Color = MaterialTheme.colorScheme.onBackground,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(
            onClick = onClick,
        ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .padding(end = 16.dp),
        )
            }
        Text(
            text = text,
            fontSize = 18.sp,
            color = textColor,
            modifier = Modifier.weight(1f)
        )
    }
}