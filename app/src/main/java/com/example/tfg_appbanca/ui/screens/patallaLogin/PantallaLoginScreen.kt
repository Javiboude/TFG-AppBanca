package com.example.httpclienttest.ui.screens.patallaLogin

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.httpclienttest.ui.navigation.Destinations
import com.example.tfg_appbanca.R

@Composable
fun PantallaLoginScreen(
    navController: NavController,
    pantallaLoginViewModel: PantallaLoginViewModel) {

    // Wstados del ViewModel
    val phoneNumber = pantallaLoginViewModel.phoneNumber
    val password = pantallaLoginViewModel.password
    val amount = pantallaLoginViewModel.amount
    val isPasswordVisible = pantallaLoginViewModel.isPasswordVisible

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Imagen en la parte superior con bordes redondeados
        Image(
            painter = painterResource(id = R.drawable.fotologin), // Carga la imagen desde recursos
            contentDescription = "Imagen Edificios", // Descripción para accesibilidad
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth() // Ocupa todo el ancho
                .height(290.dp) // Altura fija para la imagen
                .clip(
                    RoundedCornerShape(
                        bottomEnd = 16.dp,
                        bottomStart = 16.dp
                    )
                ) // Redondea los bordes
        )

        // Texto "Login" dentro de la imagen, en la parte inferior izquierda
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp) // Misma altura que la imagen
                .padding(16.dp) // Padding para el texto
        ) {
            Text(
                text = "Login",
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
                .padding(top = 350.dp), // Ajusta el espacio para que no se superponga con la imagen
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Campo de número de teléfono
            OutlinedTextField(
                value = phoneNumber.value,
                onValueChange = { phoneNumber.value = it },
                label = { Text("Número de teléfono") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth()
            )

            // Campo de contraseña
            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Contraseña") },
                visualTransformation = if (isPasswordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(onClick = { pantallaLoginViewModel.togglePasswordVisibility() }) {
                        Icon(
                            painter = if (isPasswordVisible.value) painterResource(id = R.drawable.eyeopen) // Icono de ojo abierto
                            else painterResource(id = R.drawable.eyeclose), // Icono de ojo cerrado
                            contentDescription = if (isPasswordVisible.value) "Ocultar contraseña" else "Mostrar contraseña"
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            // Campo de cantidad de dinero (opcional)
            OutlinedTextField(
                value = amount.value,
                onValueChange = { amount.value = it },
                label = { Text("Cantidad de dinero en cuenta") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            // Botón "Siguiente"
            Button(
                onClick = {
                    navController.navigate("${Destinations.PANTALLA_INICIO_URL}")
                },
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
                text = "¿No ha hecho el registro? AQUÍ",
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth() // Ocupa todo el ancho disponible
                    .wrapContentWidth(align = Alignment.CenterHorizontally) // Centra horizontalmente
                    .padding(top = 8.dp)
                    .clickable {
                        navController.navigate("${Destinations.CREAR_CUENTA_1_URL}")
                    }
            )
        }
    }
}