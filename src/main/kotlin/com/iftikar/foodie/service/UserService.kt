package com.iftikar.foodie.service

import com.iftikar.foodie.enities.User
import org.springframework.stereotype.Service

interface UserService {
    fun saveUser(user: User): User
    fun updateUser(user: User, userId: String): User
}