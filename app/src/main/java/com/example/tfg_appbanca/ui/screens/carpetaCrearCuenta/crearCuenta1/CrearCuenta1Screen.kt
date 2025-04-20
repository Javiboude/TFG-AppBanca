package com.example.httpclienttest.ui.screens.crearCuenta1

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.background
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.httpclienttest.ui.navigation.Destinations
import com.example.tfg_appbanca.R

@Composable
fun CrearCuenta1Screen(
    navController: NavController,
    crearCeunta1ViewModel: CrearCuenta1ViewModel) {
    // Acceso a los estados del ViewModel
    val phoneNumber = crearCeunta1ViewModel.phoneNumber
    val name = crearCeunta1ViewModel.name

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Imagen en la parte superior con bordes redondeados
        Image(
            painter = painterResource(id = R.drawable.fotoregistro),
            contentDescription = "Imagen Edificios",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth() // Ocupa todo el ancho
                .height(290.dp) // Altura fija para la imagen
                .clip(RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)) // Redondea los bordes
        )
        // Texto "Crear Cuenta" dentro de la imagen, en la parte inferior izquierda
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp) // Misma altura que la imagen
                .padding(15.dp) // Padding para el texto
        ) {
            Text(
                text = "Crear Cuenta",
                color = Color.White, // Color del texto
                fontSize = 36.sp,
                modifier = Modifier
                    .align(Alignment.BottomStart) // Alinea el texto en la parte inferior izquierda
            )
        }

        // Contenido principal debajo de la imagen
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(top = 280.dp), // Ajusta el espacio para que no se superponga con la imagen
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Spacer(modifier = Modifier.height(10.dp))

            // Icono de teléfono en círculos concéntricos
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF0D47A1)) // Azul más oscuro
            ) {
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = "Teléfono",
                    tint = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Campo de número de teléfono
            OutlinedTextField(
                value = phoneNumber.value,
                onValueChange = { phoneNumber.value = it },
                label = { Text("Número de teléfono") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth()
            )

            // Campo de número de teléfono
            OutlinedTextField(
                value = name.value,
                onValueChange = { name.value = it },
                label = { Text("Código de verificacion") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth()
            )

            // Botón "Siguiente"
            Button(
                onClick = {navController.navigate("${Destinations.CREAR_CUENTA_2_URL}") },
                shape = RoundedCornerShape(50),
                border = BorderStroke(1.dp, Color.Black), // Borde negro
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text(text = "Siguiente", fontSize = 18.sp)
            }

            // Enlace para registro
            Text(
                text = "¿Ya tiene una cuenta? LOGIN",
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth() // Ocupa todo el ancho disponible
                    .wrapContentWidth(align = Alignment.CenterHorizontally) // Centra horizontalmente
                    .padding(top = 8.dp)
                    .clickable { navController.navigate("${Destinations.PANTALLA_LOGIN_URL}") }
            )
        }
    }
}