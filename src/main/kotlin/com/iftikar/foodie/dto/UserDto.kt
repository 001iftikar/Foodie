package com.iftikar.foodie.dto

data class UserDto(
    val id: String = "",
    val name: String,
    val email: String,
    val password: String,
    val address: String,
    val phoneNumber: String
)
