package com.example.userlist.data.remote

import com.example.userlist.domain.model.User
import kotlinx.serialization.Serializable

@Serializable
data class UsersResponseDto(
    val users: List<UserDto>,
    val total: Int,
    val skip: Int,
    val limit: Int,
)

@Serializable
data class UserDto(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val image: String,
    val username: String,
)

fun UserDto.toDomain(): User = User(
    id = id,
    firstName = firstName,
    lastName = lastName,
    email = email,
    phone = phone,
    imageUrl = image,
    username = username,
)
