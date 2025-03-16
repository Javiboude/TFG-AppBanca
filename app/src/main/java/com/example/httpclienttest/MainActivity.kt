package com.example.httpclienttest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import com.example.httpclienttest.ui.screens.pantallaDeCarga.PantallaDeCargaScreen
import com.example.httpclienttest.ui.screens.pantallaDeCarga.PantallaDeCargaViewModel
import com.example.httpclienttest.ui.theme.HttpClientTestTheme

class MainActivity : ComponentActivity() {

    private val pantallaDeCargaViewModel: PantallaDeCargaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HttpClientTestTheme {
                Surface {
                    PantallaDeCargaScreen(pantallaDeCargaViewModel)
                }

            }
        }
    }
}