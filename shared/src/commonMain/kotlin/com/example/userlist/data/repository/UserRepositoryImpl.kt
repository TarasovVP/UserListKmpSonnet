package com.example.userlist.data.repository

import com.example.userlist.data.local.UserLocalDataSource
import com.example.userlist.data.remote.UserRemoteDataSource
import com.example.userlist.data.remote.toDomain
import com.example.userlist.domain.model.User
import com.example.userlist.domain.repository.UserRepository

class UserRepositoryImpl(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource,
) : UserRepository {

    override suspend fun getUsers(): List<User> {
        return try {
            val remoteUsers = remoteDataSource.getUsers().map { it.toDomain() }
            localDataSource.saveUsers(remoteUsers)
            remoteUsers
        } catch (e: Exception) {
            val cachedUsers = localDataSource.getUsers()
            if (cachedUsers.isEmpty()) throw e
            cachedUsers
        }
    }
}
