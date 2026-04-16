package com.example.userlist.data.local

import com.example.userlist.domain.model.User

interface UserLocalDataSource {
    suspend fun getUsers(): List<User>
    suspend fun saveUsers(users: List<User>)
    suspend fun clearUsers()
}
