package com.example.userlist.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

private const val BASE_URL = "https://dummyjson.com"

interface UserRemoteDataSource {
    suspend fun getUsers(): List<UserDto>
}

class UserApi(private val httpClient: HttpClient) : UserRemoteDataSource {

    override suspend fun getUsers(): List<UserDto> =
        httpClient.get("$BASE_URL/users?limit=100").body<UsersResponseDto>().users
}
