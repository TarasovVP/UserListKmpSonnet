package com.example.userlist.domain.repository

import com.example.userlist.domain.model.User

interface UserRepository {
    suspend fun getUsers(): List<User>
}
