package com.iftikar.foodie.service

import com.iftikar.foodie.dto.UserDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface UserService {
    fun saveUser(user: UserDto): UserDto
    fun updateUser(user: UserDto, userId: String): UserDto
    fun getUsers(pageable: Pageable): Page<UserDto>
    fun getByUserName(name: String): List<UserDto>
    fun getByEmail(email: String): UserDto
    fun getById(id: String): UserDto
    fun deleteUser(userId: String)

    // some other operations
    fun searchUserByName(keyword: String): List<UserDto>
}