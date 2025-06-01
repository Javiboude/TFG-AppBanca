package com.example.tfg_appbanca.ui.components.topAppBar

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.httpclienttest.ui.navigation.Destinations
import com.example.tfg_appbanca.R
import com.example.tfg_appbanca.ui.screens.patallaLogin.SharedViewModel

@SuppressLint("UnrememberedGetBackStackEntry")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Topbar(
    currentRoute: MutableState<String>,
    topAppBarViewModel: TopAppBarViewModel = hiltViewModel(),
    navController: NavController,
) {

    val estadoPagina by remember { derivedStateOf {
        if (currentRoute.value == Destinations.PANTALLA_INICIO_URL) {
            "Inicio"
        } else if (currentRoute.value == Destinations.PANTALLA_TARJETAS_URL){
            "Tarjetas"
        } else if (currentRoute.value == Destinations.PANTALLA_AHORROS_URL){
            "Ahorros"
        }else{
            ""
        }
    }
    }

    val sharedViewModel: SharedViewModel = hiltViewModel(
        navController.getBackStackEntry(Destinations.PANTALLA_DE_CARGA_URL)
    )

    val usuario by topAppBarViewModel.usuario.collectAsStateWithLifecycle()
    val numeroTelefono by sharedViewModel.numeroTelefono.collectAsStateWithLifecycle()

    val reloadUser by topAppBarViewModel.reloadUser.collectAsStateWithLifecycle()

    LaunchedEffect(numeroTelefono, reloadUser) {
        if (numeroTelefono.isNotEmpty()) {
            topAppBarViewModel.getUsuarioInfo(numeroTelefono)
        }
    }

    val topBarModifier = Modifier
        .fillMaxWidth()
        .height(72.dp)
        .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
        .border(
            width = 1.dp,
            color = Color.LightGray,
            shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
        )

    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(start = 13.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.person),
                            contentDescription = "Avatar",
                            modifier = Modifier.size(44.dp)
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Column {
                            Text(
                                text = "Hola,",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Gray
                            )
                                Text(
                                    text = usuario?.nombre ?: "Usuario",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.Black
                                )
                        }
                    }


                    IconButton(
                        onClick = {navController.navigate("${Destinations.PANTALLA_AJUSTES}") },
                        modifier = Modifier.padding(start = 20.dp, top = 15.dp, end = 10.dp)
                    ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ajustes),
                        contentDescription = "Ajustes",
                        modifier = Modifier
                            .size(35.dp),
                        tint = Color.Black
                    )
                    }
                }

                Text(
                    estadoPagina,
                    fontSize = 27.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center,

                )
            }

        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black
        ),
        modifier = topBarModifier

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar2(
    currentRoute: String,  // Cambiado a String en lugar de MutableState<String>
    onHomeClick: () -> Unit,
    onTarjetClick: () -> Unit,
    onAjustesClick: () -> Unit
) {
    val title by remember { derivedStateOf {
        if (currentRoute == Destinations.PANTALLA_BIZUM_URL) {
            "Bizum"
        } else if (currentRoute == Destinations.PANTALLA_TRANSFERENCIA_URL){
            "Transferencia"
        } else if (currentRoute == Destinations.PANTALLA_MODIFICAR_LIMITES_URL){
            "Modificar limites"
        }else if (currentRoute == Destinations.PANTALLA_CANCELAR_TARJETA_URL){
            "Info tarjeta"
        } else if (currentRoute == Destinations.PANTALLA_AJUSTES){
            "Ajustes"
        } else if (currentRoute == Destinations.PANTALLA_AÑADIR_DINERO){
            "Añadir dinero"
        } else {
            "Datos personales"
        }
    }
    }

    val onClick: () -> Unit = {
        if (currentRoute == Destinations.PANTALLA_BIZUM_URL || currentRoute == Destinations.PANTALLA_TRANSFERENCIA_URL || currentRoute == Destinations.PANTALLA_AJUSTES) {
            onHomeClick()
        } else if (currentRoute == Destinations.PANTALLA_MODIFICAR_LIMITES_URL || currentRoute == Destinations.PANTALLA_CANCELAR_TARJETA_URL) {
            onTarjetClick()
        } else {
            onAjustesClick()
        }
    }

    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
            ),
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, end = 55.dp),
                    textAlign = TextAlign.Center
                )
            }
        },
        navigationIcon = {
            IconButton(
                onClick = onClick,
                modifier = Modifier.padding(start = 20.dp, top = 15.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.flechaizquierda),
                    contentDescription = "Flecha atrás",
                    modifier = Modifier.size(44.dp),
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        },
    )
}