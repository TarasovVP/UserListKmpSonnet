package com.example.userlist.di

import com.example.userlist.data.local.InMemoryUserLocalDataSource
import com.example.userlist.data.local.UserLocalDataSource
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.js.Js
import org.koin.dsl.module

actual val platformModule = module {
    single<HttpClientEngine> { Js.create() }
    single<UserLocalDataSource> { InMemoryUserLocalDataSource() }
}
