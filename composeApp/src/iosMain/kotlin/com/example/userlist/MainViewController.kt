package com.example.userlist

import androidx.compose.ui.window.ComposeUIViewController
import com.example.userlist.di.appModule
import com.example.userlist.di.dataPlatformModule
import com.example.userlist.di.initKoin

private var koinStarted = false

fun MainViewController() = ComposeUIViewController(
    configure = {
        if (!koinStarted) {
            koinStarted = true
            initKoin(extraModules = listOf(dataPlatformModule, appModule))
        }
    },
) {
    App()
}
