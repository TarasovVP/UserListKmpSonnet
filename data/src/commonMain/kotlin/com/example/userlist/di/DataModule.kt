package com.example.userlist.di

import com.example.userlist.data.local.DatabaseDriverFactory
import com.example.userlist.data.local.SqlDelightUserLocalDataSource
import com.example.userlist.data.local.UserLocalDataSource
import com.example.userlist.db.UserDatabase
import org.koin.dsl.module

val dataModule = module {
    single<UserLocalDataSource> {
        SqlDelightUserLocalDataSource(UserDatabase(get<DatabaseDriverFactory>().createDriver()))
    }
}
