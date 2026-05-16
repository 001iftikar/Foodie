package com.iftikar.foodie.payload

import com.iftikar.foodie.dto.UserDto

data class JwtResponse(
    val accessToken: String,
    val refreshToken: String,
    val user: UserDto
)
