package com.iftikar.foodie.dto

import com.iftikar.foodie.enities.Restaurant
import com.iftikar.foodie.enities.RoleEntity

data class UserDto(
    val id: String = "",
    val name: String,
    val email: String,
    val password: String,
    val address: String,
    val phoneNumber: String,
    val restaurants: List<Restaurant> = emptyList(),
    val roleEntities: List<RoleEntity> = emptyList()
)
