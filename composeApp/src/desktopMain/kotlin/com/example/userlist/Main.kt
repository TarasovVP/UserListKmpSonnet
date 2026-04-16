package com.example.userlist

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.example.userlist.di.appModule
import com.example.userlist.di.dataPlatformModule
import com.example.userlist.di.initKoin

fun main() {
    initKoin(extraModules = listOf(dataPlatformModule, appModule))
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "User List",
            state = rememberWindowState(width = 420.dp, height = 720.dp),
        ) {
            App()
        }
    }
}
