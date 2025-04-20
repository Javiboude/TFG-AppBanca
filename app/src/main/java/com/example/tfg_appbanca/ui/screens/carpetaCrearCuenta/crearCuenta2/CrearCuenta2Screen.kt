package com.example.httpclienttest.ui.screens.crearCuenta2

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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.IconButton
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.httpclienttest.ui.navigation.Destinations
import com.example.httpclienttest.ui.screens.crearCuenta1.CrearCuenta1ViewModel
import com.example.tfg_appbanca.R

@Composable
fun CrearCuenta2Screen(
    navController: NavController,
    crearCuenta2ViewModel: CrearCuenta2ViewModel) {
    // Acceso a los estados del ViewModel
    val name = crearCuenta2ViewModel.name
    val email = crearCuenta2ViewModel.email
    val password = crearCuenta2ViewModel.password
    val isPasswordVisible = crearCuenta2ViewModel.isPasswordVisible

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
                .clip(
                    RoundedCornerShape(
                        bottomEnd = 16.dp,
                        bottomStart = 16.dp
                    )
                ) // Redondea los bordes
        )

        // Texto "Crear Cuenta" dentro de la imagen, en la parte inferior izquierda
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp) // Misma altura que la imagen
                .padding(16.dp) // Padding para el texto
        ) {
            Text(
                text = "Informacion Personal",
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

            Spacer(modifier = Modifier.height(15.dp))

            // Icono de teléfono en círculos concéntricos
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF0D47A1)) // Azul más oscuro
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Persona",
                    tint = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Campo de número de teléfono
            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("Nombre") },
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
                    IconButton(onClick = { crearCuenta2ViewModel.togglePasswordVisibility() }) {
                        Icon(
                            painter = if (isPasswordVisible.value) painterResource(id = R.drawable.eyeopen) // Icono de ojo abierto
                            else painterResource(id = R.drawable.eyeclose), // Icono de ojo cerrado
                            contentDescription = if (isPasswordVisible.value) "Ocultar contraseña" else "Mostrar contraseña"
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            // Campo de contraseña
            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Confirmacion de contraseña") },
                visualTransformation = if (isPasswordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(onClick = { crearCuenta2ViewModel.togglePasswordVisibility() }) {
                        Icon(
                            painter = if (isPasswordVisible.value) painterResource(id = R.drawable.eyeopen) // Icono de ojo abierto
                            else painterResource(id = R.drawable.eyeclose), // Icono de ojo cerrado
                            contentDescription = if (isPasswordVisible.value) "Ocultar contraseña" else "Mostrar contraseña"
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            // Espaciado entre campos y botones
            Spacer(modifier = Modifier.height(16.dp))

            // Fila con los botones "Atrás" y "Siguiente"
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { navController.navigate("${Destinations.CREAR_CUENTA_1_URL}") },
                    shape = RoundedCornerShape(50),
                    border = BorderStroke(1.dp, Color.Black), // Borde negro
                    modifier = Modifier
                        .weight(1f) // Cada botón ocupa la mitad del ancho
                        .padding(end = 8.dp) // Espacio entre botones
                ) {
                    Text(text = "Atrás", fontSize = 18.sp)
                }

                Button(
                    onClick = {  navController.navigate("${Destinations.PANTALLA_INICIO_URL}") },
                    shape = RoundedCornerShape(50),
                    border = BorderStroke(1.dp, Color.Black), // Borde negro
                    modifier = Modifier
                        .weight(1f) // Cada botón ocupa la mitad del ancho
                        .padding(start = 8.dp) // Espacio entre botones
                ) {
                    Text(text = "Siguiente", fontSize = 18.sp)
                }
            }
        }
    }
}
