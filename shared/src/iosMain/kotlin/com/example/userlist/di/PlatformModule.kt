package com.example.userlist.di

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin
import org.koin.dsl.module

actual val platformModule = module {
    single<HttpClientEngineFactory<*>> { Darwin }
}
