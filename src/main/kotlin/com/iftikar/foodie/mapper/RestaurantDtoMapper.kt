package com.iftikar.foodie.mapper

import com.iftikar.foodie.dto.RestaurantDto
import com.iftikar.foodie.enities.Restaurant

fun RestaurantDto.toEntity() = Restaurant(
    name = name,
    description = description,
    openTime = openTime,
    closeTime = closeTime,
    image = image
)

fun Restaurant.toDto() = RestaurantDto(
    id = this.id ?: "",
    name = name,
    description = description,
    openTime = openTime,
    closeTime = closeTime,
    image = image ?: ""
)