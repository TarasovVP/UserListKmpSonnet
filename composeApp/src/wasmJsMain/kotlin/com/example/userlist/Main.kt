package com.example.userlist

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import com.example.userlist.di.appModule
import com.example.userlist.di.initKoin

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    initKoin(extraModules = listOf(appModule))
    CanvasBasedWindow(canvasElementId = "ComposeTarget") {
        App()
    }
}
