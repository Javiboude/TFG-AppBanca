package com.example.httpclienttest.ui.screens.patallaLogin

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.httpclienttest.R

@Composable
fun PantallaLoginScreen(pantallaLoginViewModel: PantallaLoginViewModel) {
    // Estado para los campos de entrada
    var phoneNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Imagen en la parte superior con bordes redondeados
        Image(
            painter = painterResource(id = R.drawable.fotologin), // Carga la imagen desde recursos
            contentDescription = "Imagen Edificios", // Descripción para accesibilidad
            modifier = Modifier
                .fillMaxWidth() // Ocupa todo el ancho
                .height(257.dp) // Altura fija para la imagen
                .clip(RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)) // Redondea los bordes
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
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text("Número de teléfono") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier
                    .fillMaxWidth()
            )

            // Campo de contraseña
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            // Campo de cantidad de dinero (opcional)
            OutlinedTextField(
                value = amount,
                onValueChange = { amount = it },
                label = { Text("Cantidad de dinero") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            // Botón "Siguiente"
            Button(
                onClick = { /* Implementa la lógica aquí */ },
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
                color = Color.Blue,
                modifier = Modifier
                    .fillMaxWidth() // Ocupa todo el ancho disponible
                    .wrapContentWidth(align = Alignment.CenterHorizontally) // Centra horizontalmente
                    .padding(top = 8.dp)
                    .clickable { /* Implementa la lógica aquí */ }
            )
        }
    }
}