package com.example.httpclienttest.ui.screens.patallaLogin

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.httpclienttest.ui.navigation.Destinations
import com.example.tfg_appbanca.R
import com.example.tfg_appbanca.ui.screens.patallaLogin.PantallaLoginViewModel
import com.example.tfg_appbanca.ui.screens.patallaLogin.SharedViewModel


@Composable
fun PantallaLoginScreen(
    navController: NavController,
    pantallaLoginViewModel: PantallaLoginViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel = hiltViewModel()
) {
    val numeroTelefono = pantallaLoginViewModel.numeroTelefono
    val contraseña = pantallaLoginViewModel.contraseña
    val contraseñaVisible = pantallaLoginViewModel.contraseñaVisible

    val loginExitoso by pantallaLoginViewModel.loginExitoso
    val loginFallido by pantallaLoginViewModel.loginFallido

    val camposCompletos = numeroTelefono.value.isNotBlank() &&
            contraseña.value.isNotBlank()

    if (loginFallido == true) {
        androidx.compose.material3.AlertDialog(
            onDismissRequest = { },
            confirmButton = {
                Button(onClick = {
                    pantallaLoginViewModel.setRegistroFallido(false)
                }) {
                    Text("Aceptar")
                }
            },
            title = { Text("Login Fallido") },
            text = { Text("No se ha podido iniciar el usuario.") }
        )
    }

    LaunchedEffect(loginExitoso) {
        if (loginExitoso) {
            sharedViewModel.setNumeroTelefono(numeroTelefono.value)
            navController.navigate(Destinations.PANTALLA_INICIO_URL)
            pantallaLoginViewModel.setLoginExitoso(false)
        }
    }



    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.fotologin),
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
                .height(260.dp)
                .padding(16.dp)
        ) {
            Text(
                text = "Login",
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
                .padding(top = 350.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            OutlinedTextField(
                value = numeroTelefono.value,
                onValueChange = { numeroTelefono.value = it },
                label = { Text("Número de teléfono") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = contraseña.value,
                onValueChange = { contraseña.value = it },
                label = { Text("Contraseña") },
                visualTransformation = if (contraseñaVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(onClick = { pantallaLoginViewModel.togglePasswordVisibility() }) {
                        Icon(
                            painter = if (contraseñaVisible.value) painterResource(id = R.drawable.eyeopen)
                            else painterResource(id = R.drawable.eyeclose),
                            contentDescription = if (contraseñaVisible.value) "Ocultar contraseña" else "Mostrar contraseña"
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    pantallaLoginViewModel.login()
                },
                shape = RoundedCornerShape(50),
                border = BorderStroke(1.dp, Color.Black),
                enabled = camposCompletos,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text(text = "Siguiente", fontSize = 18.sp)
            }
            Text(
                text = "¿No ha hecho el registro? AQUÍ",
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(align = Alignment.CenterHorizontally)
                    .padding(top = 8.dp)
                    .clickable {
                        navController.navigate("${Destinations.CREAR_CUENTA_URL}")
                    }
            )
        }
    }
}