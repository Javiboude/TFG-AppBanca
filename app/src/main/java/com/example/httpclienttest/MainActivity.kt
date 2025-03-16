package com.example.httpclienttest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import com.example.httpclienttest.ui.screens.user.UserListScreen
import com.example.httpclienttest.ui.screens.user.UserListViewModel
import com.example.httpclienttest.ui.theme.HttpClientTestTheme

class MainActivity : ComponentActivity() {

    private val userListViewModel: UserListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HttpClientTestTheme {
                Surface {
                    UserListScreen(userListViewModel)
                }

            }
        }
    }
}