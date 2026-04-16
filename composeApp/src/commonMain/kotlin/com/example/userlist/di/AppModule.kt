package com.example.userlist.di

import com.example.userlist.presentation.UserListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::UserListViewModel)
}
