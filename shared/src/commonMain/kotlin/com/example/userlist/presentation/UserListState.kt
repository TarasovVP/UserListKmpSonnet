package com.example.userlist.presentation

import com.example.userlist.domain.model.User

sealed class UserListState {
    data object Loading : UserListState()
    data class Success(val users: List<User>) : UserListState()
    data class Error(val message: String) : UserListState()
    data object Empty : UserListState()
}
