package com.iftikar.foodie.dto

import com.iftikar.foodie.enities.User

data class RestaurantDto(
    val id: String = "",
    val name: String,
    val description: String,
    val openTime: Long,
    val closeTime: Long,
    val isOpen: Boolean = true,
    val image: String
)
