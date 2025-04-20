package com.example.tfg_appbanca.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.clip
import com.example.httpclienttest.ui.navigation.Destinations

@Composable
fun BottomNavigationBar(
    currentRoute: String,
    onHomeClick: () -> Unit,
    onCardsClick: () -> Unit,
    onSavingsClick: () -> Unit
) {
    NavigationBar(
        containerColor = Color(0xFF0b1e61), // Azul oscuro
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)) // Bordes redondeados arriba
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Item para Inicio
            NavigationBarItem(
                selected = currentRoute == Destinations.PANTALLA_INICIO_URL,
                onClick = onHomeClick,
                label = { Text("Inicio", color = Color.White) }, // Texto blanco
                icon = { Icon(Icons.Filled.Home, contentDescription = "Inicio") } // Ícono blanco
            )

            // Item para Tarjetas
            NavigationBarItem(
                selected = currentRoute == Destinations.PANTALLA_TARJETAS_URL,
                onClick = onCardsClick,
                label = { Text("Tarjetas", color = Color.White) }, // Texto blanco
                icon = {
                    Icon(
                        modifier = Modifier.size(25.dp),
                        painter = painterResource(id = com.example.tfg_appbanca.R.drawable.wallet),
                        contentDescription = "Tarjetas",
                    )
                }
            )

            // Item para Ahorros
            NavigationBarItem(
                selected = currentRoute == Destinations.PANTALLA_AHORROS_URL,
                onClick = onSavingsClick,
                label = { Text("Ahorros", color = Color.White) }, // Texto blanco
                icon = {
                    Icon(
                        modifier = Modifier.size(25.dp),
                        painter = painterResource(id = com.example.tfg_appbanca.R.drawable.savings),
                        contentDescription = "Ahorros",
                    )
                }
            )
        }
    }
}
