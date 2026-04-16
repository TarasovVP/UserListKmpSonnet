package com.example.userlist.domain.model

data class User(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val imageUrl: String,
    val username: String,
) {
    val fullName: String get() = "$firstName $lastName"
}
