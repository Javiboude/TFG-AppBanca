
package com.example.httpclienttest.ui.screens.pantallaInicio

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.tfg_appbanca.ui.screens.pantallaInicio.PantallaInicioViewModel
import com.example.tfg_appbanca.ui.screens.patallaLogin.SharedViewModel

@Composable
fun PantallaInicioScreen(
    navController: NavController,
    pantallaInicioViewModel: PantallaInicioViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel = hiltViewModel()
) {
    val numeroTelefono by sharedViewModel.numeroTelefono.collectAsStateWithLifecycle()
    val contactos by pantallaInicioViewModel.contactos.collectAsStateWithLifecycle()
    val balanceDinero by pantallaInicioViewModel.balanceDinero.collectAsStateWithLifecycle()
    val usuario by pantallaInicioViewModel.usuario.collectAsStateWithLifecycle()
    val ultimosMovimientos by pantallaInicioViewModel.ultimosMovimientos.collectAsStateWithLifecycle()
    val isLoading by pantallaInicioViewModel.isLoading.collectAsStateWithLifecycle()

    LaunchedEffect(numeroTelefono) {
        pantallaInicioViewModel.cargarDatosUsuario(numeroTelefono)
    }

    if (isLoading) {
        CircularProgressIndicator(modifier = Modifier.fillMaxSize().wrapContentSize())
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
        item {
            balanceDinero?.let {
                if (usuario != null) {
                    TablaIngresos(
                        ingresos = it.ingresos,
                        gastos = it.gastos,
                        meses = it.meses,
                        balanceTotal = usuario!!.dinero,
                    )
                }
            }
        }


        item { Spacer(modifier = Modifier.height(32.dp)) }

        item {
            AccionesRapidas(
                onBizumClick = {navController.navigate("${Destinations.PANTALLA_BIZUM_URL}") },
                onTransferenciaClick =  { navController.navigate("${Destinations.PANTALLA_TRANSFERENCIA_URL}") }
            )
        }

        item { Spacer(modifier = Modifier.height(32.dp)) }

        item {
            ContactosRecientes(
                contactos = contactos
            )
        }

        item { Spacer(modifier = Modifier.height(32.dp)) }


        item {
            Text(
                text = "Historial de movimientos",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.fillMaxWidth()
            )
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        items(ultimosMovimientos) { movimiento ->
            TransaccionesRecientes(
                cantidad = movimiento.cantidad,
                esPositiva = movimiento.es_positiva,
                lugar = movimiento.lugar,
                motivo = movimiento.motivo
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}
}


@Composable
private fun AccionesRapidas(
    onBizumClick: () -> Unit,
    onTransferenciaClick: () -> Unit
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
            horizontalArrangement = Arrangement.spacedBy(100.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.wrapContentWidth()
        ) {
            BotonAccionRapida(
                icono = R.drawable.bizum,
                texto = "Bizum",
                onClick = onBizumClick
            )

            BotonAccionRapida(
                icono = R.drawable.tranferencia,
                texto = "Transferencia",
                onClick = onTransferenciaClick
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
private fun ContactosRecientes(
    contactos: List<String>
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Contactos Recientes",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(contactos) { contact ->
                ContactoItem(
                    inicialContacto = contact,
                )
            }
        }
    }
}

@Composable
private fun ContactoItem(
    inicialContacto: String
) {
    Card(
        shape = CircleShape,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        modifier = Modifier
            .size(60.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = inicialContacto,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun TransaccionesRecientes(
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

@Composable
fun TablaIngresos(
    ingresos: List<Float>,
    gastos: List<Float>,
    meses: List<String>,
    balanceTotal: Float
) {
    val valorMaximo = maxOf(ingresos.maxOrNull() ?: 0f, gastos.maxOrNull() ?: 0f)
    val barra = 20.dp

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF0A1D57))
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Row() {
            Text("Balance total", color = Color.White, fontSize = 20.sp)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "$${balanceTotal}",
                        color = Color.White,
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(30.dp),
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
        ) {
            meses.indices.forEach { i ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    // Barra ingresos (verde)
                    Box(
                        modifier = Modifier
                            .height((100 * ingresos[i] / valorMaximo).dp)
                            .width(barra)
                            .background(Color(0xFF00E676))
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    // Barra gastos (rojo)
                    Box(
                        modifier = Modifier
                            .height((100 * gastos[i] / valorMaximo).dp)
                            .width(barra)
                            .background(Color(0xFFFF5252))
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(meses[i], color = Color.White, fontSize = 12.sp)
                }
            }
        }
    }
}