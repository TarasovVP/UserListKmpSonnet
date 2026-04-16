package com.example.userlist.data.local

import com.example.userlist.UserEntity
import com.example.userlist.db.UserDatabase
import com.example.userlist.domain.model.User

class SqlDelightUserLocalDataSource(
    private val database: UserDatabase,
) : UserLocalDataSource {

    override suspend fun getUsers(): List<User> =
        database.userEntityQueries.selectAll().executeAsList().map { it.toDomain() }

    override suspend fun saveUsers(users: List<User>) {
        database.userEntityQueries.transaction {
            database.userEntityQueries.deleteAll()
            users.forEach { user ->
                database.userEntityQueries.insertOrReplace(
                    id = user.id,
                    firstName = user.firstName,
                    lastName = user.lastName,
                    email = user.email,
                    phone = user.phone,
                    imageUrl = user.imageUrl,
                    username = user.username,
                )
            }
        }
    }

    override suspend fun clearUsers() {
        database.userEntityQueries.deleteAll()
    }

    private fun UserEntity.toDomain(): User = User(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email,
        phone = phone,
        imageUrl = imageUrl,
        username = username,
    )
}
