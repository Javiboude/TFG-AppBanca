// PantallaInicioScreen.kt
package com.example.httpclienttest.ui.screens.pantallaInicio

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.httpclienttest.ui.navigation.Destinations
import com.example.tfg_appbanca.R
import com.example.tfg_appbanca.ui.screens.pantallaInicio.PantallaInicioViewModel
import com.example.tfg_appbanca.ui.screens.pantallaInicio.QuickAction
import com.example.tfg_appbanca.ui.screens.pantallaInicio.Transaction

@Composable
fun PantallaInicioScreen(
    navController: NavController,
    pantallaInicioViewModel: PantallaInicioViewModel
) {
    val uiState = pantallaInicioViewModel.uiState.collectAsStateWithLifecycle().value

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Gráfico de balance
        item {
            BalanceChart(
                ingresos = uiState.ingresos,
                gastos = uiState.gastos,
                meses = uiState.meses,
                balanceTotal = uiState.balanceTotal,
                porcentajeCambio = uiState.porcentajeCambio
            )
        }

        item { Spacer(modifier = Modifier.height(32.dp)) }

        // Quick actions section
        item {
            QuickActionsSection(
                onBizumClick = {navController.navigate("${Destinations.PANTALLA_BIZUM_URL}") },
                onTransferClick = { navController.navigate("${Destinations.PANTALLA_TRANSFERENCIA_URL}") }
            )
        }

        item { Spacer(modifier = Modifier.height(32.dp)) }

        // Recent contacts section
        item {
            RecentContactsSection(
                contacts = uiState.contacts,
                onContactClick = { contact -> pantallaInicioViewModel.onContactClicked(contact) }
            )
        }

        item { Spacer(modifier = Modifier.height(32.dp)) }

        // Transaction history header
        item {
            Text(
                text = "Historial de movimientos",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.fillMaxWidth()
            )
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        // Transaction list
        items(uiState.recentTransactions) { transaction ->
            TransactionItem(transaction = transaction)
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
private fun QuickActionsSection(
    onBizumClick: () -> Unit,
    onTransferClick: () -> Unit
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
            QuickActionButton(
                icon = R.drawable.bizum,
                label = "Bizum",
                onClick = onBizumClick
            )

            QuickActionButton(
                icon = R.drawable.tranferencia,
                label = "Transferencia",
                onClick = onTransferClick
            )
        }
    }
}

@Composable
private fun QuickActionButton(
    icon: Int,
    label: String,
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
                painter = painterResource(id = icon),
                contentDescription = label,
                modifier = Modifier.size(32.dp))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
private fun RecentContactsSection(
    contacts: List<String>,
    onContactClick: (String) -> Unit
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
            items(contacts) { contact ->
                ContactItem(
                    contactInitials = contact,
                    onClick = { onContactClick(contact) }
                )
            }
        }
    }
}

@Composable
private fun ContactItem(
    contactInitials: String,
    onClick: () -> Unit
) {
    Card(
        shape = CircleShape,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        modifier = Modifier
            .size(60.dp)
            .clickable(onClick = onClick)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = contactInitials,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun TransactionItem(transaction: Transaction) {
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
                    text = transaction.reason,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = transaction.place,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }

            Text(
                text = "${if (transaction.isPositive) "+" else "-"}${"%.2f".format(transaction.amount)}€",
                style = MaterialTheme.typography.bodyLarge,
                color = if (transaction.isPositive) {
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
fun BalanceChart(
    ingresos: List<Float>,
    gastos: List<Float>,
    meses: List<String>,
    balanceTotal: Float,
    porcentajeCambio: Float
) {
    val maxValor = maxOf(ingresos.maxOrNull() ?: 0f, gastos.maxOrNull() ?: 0f)
    val barWidth = 20.dp
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
                        text = "$${String.format("%.2f", balanceTotal)}",
                        color = Color.White,
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Text(
                        text = "${if (porcentajeCambio >= 0) "+" else ""}${porcentajeCambio}%",
                        color = Color.White,
                        modifier = Modifier
                            .background(Color(0xFF00C853), RoundedCornerShape(6.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp),
                        fontSize = 12.sp
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
                            .height((100 * ingresos[i] / maxValor).dp)
                            .width(barWidth)
                            .background(Color(0xFF00E676))
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    // Barra gastos (rojo)
                    Box(
                        modifier = Modifier
                            .height((100 * gastos[i] / maxValor).dp)
                            .width(barWidth)
                            .background(Color(0xFFFF5252))
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(meses[i], color = Color.White, fontSize = 12.sp)
                }
            }
        }
    }
}