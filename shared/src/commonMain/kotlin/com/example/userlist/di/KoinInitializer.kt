package com.example.userlist.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module

fun initKoin(
    extraModules: List<Module> = emptyList(),
    appDeclaration: KoinApplication.() -> Unit = {},
): KoinApplication =
    startKoin {
        appDeclaration()
        modules(sharedModule, platformModule, *extraModules.toTypedArray())
    }
