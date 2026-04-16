package com.example.userlist

import androidx.compose.ui.window.ComposeUIViewController
import com.example.userlist.di.appModule
import com.example.userlist.di.dataPlatformModule
import com.example.userlist.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin(extraModules = listOf(dataPlatformModule, appModule))
    },
) {
    App()
}
