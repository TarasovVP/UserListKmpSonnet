package com.example.userlist

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.userlist.ui.UserListScreen
import org.koin.compose.KoinContext

@Composable
fun App() {
    KoinContext {
        MaterialTheme {
            UserListScreen()
        }
    }
}
