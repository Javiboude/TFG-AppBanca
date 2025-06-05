package com.example.tfg_appbanca.ui.screens.pantallaTarjetas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.httpclienttest.ui.navigation.Destinations
import com.example.tfg_appbanca.R
import com.example.tfg_appbanca.ui.screens.patallaLogin.SharedViewModel

@Composable
fun PantallaTarjetasScreen(
    navController: NavController,
    pantallaTarjetasViewModel: PantallaTarjetasViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel = hiltViewModel()
) {
    val usuario by pantallaTarjetasViewModel.usuario.collectAsStateWithLifecycle()
    val numeroTelefono by sharedViewModel.numeroTelefono.collectAsStateWithLifecycle()
    val ultimosMovimientos by pantallaTarjetasViewModel.ultimosMovimientos.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        pantallaTarjetasViewModel.cargarDatosUsuario(numeroTelefono)
    }

    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            if (usuario != null) {
            TarjetaInfo(saldo = usuario!!.dinero)
            }
            Spacer(modifier = Modifier.height(40.dp))
            AccionesRapidas(
                onModificarLimitesClick = { navController.navigate("${Destinations.PANTALLA_MODIFICAR_LIMITES_URL}") },
                onInfoTarjetaClick = { navController.navigate("${Destinations.PANTALLA_INFO_TARJETA_URL}") }
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Historial Tarjeta",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        items(ultimosMovimientos) { movimiento ->
            com.example.httpclienttest.ui.screens.pantallaInicio.TransaccionesRecientes(
                cantidad = movimiento.cantidad,
                esPositiva = movimiento.es_positiva,
                lugar = movimiento.lugar,
                motivo = movimiento.motivo
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun TarjetaInfo(saldo: Float) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0A1D57))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(verticalArrangement = Arrangement.Center) {
                Text(
                    text = "Débito",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal
                )

                Spacer(modifier = Modifier.height(12.dp))

                Image(
                    painter = painterResource(id = R.drawable.chip),
                    contentDescription = "Icono de chip",
                    modifier = Modifier.size(50.dp)
                )
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "Saldo Tarjeta",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "$$saldo",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun AccionesRapidas(
    onModificarLimitesClick: () -> Unit,
    onInfoTarjetaClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Acciones rápidas",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(90.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.wrapContentWidth()
        ) {
        BotonAccionRapida(
            icono = R.drawable.card,
            texto = "Modificar limites",
            onClick = onModificarLimitesClick
        )

        BotonAccionRapida(
            icono = R.drawable.eyeopen,
            texto = "Info Tarjeta",
            onClick = onInfoTarjetaClick
        )
    }
    }
}

@Composable
private fun BotonAccionRapida(
    icono: Int,
    texto: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier.size(64.dp),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Icon(
                painter = painterResource(id = icono),
                contentDescription = texto,
                modifier = Modifier.size(32.dp))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = texto,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
private fun TransaccionesRecientes(
    cantidad: Double,
    esPositiva: Boolean,
    lugar: String,
    motivo: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = motivo,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = lugar,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }

            Text(
                text = "${if (esPositiva) "+" else "-"}${"%.2f".format(cantidad)}€",
                style = MaterialTheme.typography.bodyLarge,
                color = if (esPositiva) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.error
                },
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}