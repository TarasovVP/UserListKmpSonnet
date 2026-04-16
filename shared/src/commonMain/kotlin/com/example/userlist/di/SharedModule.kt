package com.example.userlist.di

import com.example.userlist.data.remote.UserApi
import com.example.userlist.data.remote.UserRemoteDataSource
import com.example.userlist.data.repository.UserRepositoryImpl
import com.example.userlist.domain.repository.UserRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val sharedModule = module {
    single {
        HttpClient(get()) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.INFO
            }
        }
    }

    single<UserRemoteDataSource> { UserApi(get()) }

    single<UserRepository> { UserRepositoryImpl(get(), get()) }
}
