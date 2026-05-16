package com.iftikar.foodie.mapper

import com.iftikar.foodie.dto.UserDto
import com.iftikar.foodie.enities.Role
import com.iftikar.foodie.enities.User

fun UserDto.toUser() = User(
    name = name,
    email = email,
    password = password,
    address = address,
    phoneNumber =phoneNumber,
    role = Role.CUSTOMER,
    restaurants = restaurants.toMutableList(),
    roleEntities = roleEntities.toMutableList()
)

fun User.toDto() = UserDto(
    id = id ?: "",
    name = name,
    email = email,
    password = password,
    address = address,
    phoneNumber = phoneNumber
)
