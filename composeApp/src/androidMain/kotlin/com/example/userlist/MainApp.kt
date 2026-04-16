package com.example.userlist

import android.app.Application
import com.example.userlist.di.appModule
import com.example.userlist.di.dataPlatformModule
import com.example.userlist.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(extraModules = listOf(dataPlatformModule, appModule)) {
            androidLogger()
            androidContext(this@MainApp)
        }
    }
}
