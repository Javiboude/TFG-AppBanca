package com.example.httpclienttest.ui.screens.crearCuenta2

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.httpclienttest.ui.navigation.Destinations
import com.example.httpclienttest.ui.screens.crearCuenta1.CrearCuenta1ViewModel
import com.example.tfg_appbanca.R
import com.example.tfg_appbanca.data.model.RegisterResponse

@Composable
fun CrearCuenta2Screen(
    navController: NavController,
    crearCuenta2ViewModel: CrearCuenta2ViewModel = hiltViewModel(),
) {
    val nombre = crearCuenta2ViewModel.nombre
    val contraseña = crearCuenta2ViewModel.contraseña
    val contraseñaVisible = crearCuenta2ViewModel.contraseñaVisible
    val confirmarContraseña = crearCuenta2ViewModel.confirmarContraseña
    val numeroTelefono = crearCuenta2ViewModel.numeroTelefono

    val isLoading = crearCuenta2ViewModel.isLoading.value

    val isFormValid = nombre.value.isNotBlank() &&
            numeroTelefono.value.isNotBlank() &&
            contraseña.value.isNotBlank() &&
            confirmarContraseña.value.isNotBlank()

    // Imagen y diseño superior
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.fotoregistro),
            contentDescription = "Imagen Edificios",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(290.dp)
                .clip(
                    RoundedCornerShape(
                        bottomEnd = 16.dp,
                        bottomStart = 16.dp
                    )
                )
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .padding(16.dp)
        ) {
            Text(
                text = "Crear cuenta",
                color = Color.White,
                fontSize = 36.sp,
                modifier = Modifier
                    .align(Alignment.BottomStart)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(top = 280.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.height(15.dp))

            Spacer(modifier = Modifier.height(10.dp))

            // Campo Nombre
            OutlinedTextField(
                value = nombre.value,
                onValueChange = { nombre.value = it },
                label = { Text("Nombre") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = numeroTelefono.value,
                onValueChange = { numeroTelefono.value = it },
                label = { Text("Número de teléfono") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth()
            )

            // Campo Contraseña
            OutlinedTextField(
                value = contraseña.value,
                onValueChange = { contraseña.value = it },
                label = { Text("Contraseña") },
                visualTransformation = if (contraseñaVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(onClick = { crearCuenta2ViewModel.togglePasswordVisibility() }) {
                        Icon(
                            painter = if (contraseñaVisible.value) painterResource(id = R.drawable.eyeopen)
                            else painterResource(id = R.drawable.eyeclose),
                            contentDescription = if (contraseñaVisible.value) "Ocultar contraseña" else "Mostrar contraseña"
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            // Confirmar Contraseña
            OutlinedTextField(
                value = confirmarContraseña.value,
                onValueChange = { confirmarContraseña.value = it },
                label = { Text("Confirmación de contraseña") },
                visualTransformation = if (contraseñaVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(onClick = { crearCuenta2ViewModel.togglePasswordVisibility() }) {
                        Icon(
                            painter = if (contraseñaVisible.value) painterResource(id = R.drawable.eyeopen)
                            else painterResource(id = R.drawable.eyeclose),
                            contentDescription = if (contraseñaVisible.value) "Ocultar contraseña" else "Mostrar contraseña"
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { navController.navigate("${Destinations.PANTALLA_LOGIN_URL}") },
                    shape = RoundedCornerShape(50),
                    border = BorderStroke(1.dp, Color.Black),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                ) {
                    Text(text = "Atrás", fontSize = 18.sp)
                }

                Button(
                    onClick = {
                        if (contraseña.value != confirmarContraseña.value) {
                            Log.e("Registro", "Las contraseñas no coinciden")
                            return@Button
                        }

                        crearCuenta2ViewModel.registerUser(nombre.value, numeroTelefono.value, contraseña.value)
                        navController.navigate("${Destinations.PANTALLA_LOGIN_URL}")
                    },
                    enabled = isFormValid,
                    shape = RoundedCornerShape(50),
                    border = BorderStroke(1.dp, Color.Black),
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                ) {
                    if (isLoading) {
                        Text(text = "Registrando...")
                    } else {
                        Text(text = "Siguiente", fontSize = 18.sp)
                    }
                }
            }
        }
    }
}