package com.example.userlist.di

import com.example.userlist.data.local.DatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataPlatformModule = module {
    includes(dataModule)
    single { DatabaseDriverFactory(androidContext()) }
}
