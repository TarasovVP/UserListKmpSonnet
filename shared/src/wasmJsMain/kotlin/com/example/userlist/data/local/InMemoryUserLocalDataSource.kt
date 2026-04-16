package com.example.userlist.data.local

import com.example.userlist.domain.model.User

class InMemoryUserLocalDataSource : UserLocalDataSource {
    private var cachedUsers: List<User> = emptyList()

    override suspend fun getUsers(): List<User> = cachedUsers

    override suspend fun saveUsers(users: List<User>) {
        cachedUsers = users
    }

    override suspend fun clearUsers() {
        cachedUsers = emptyList()
    }
}
