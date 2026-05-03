package com.iftikar.foodie.repository

import com.iftikar.foodie.enities.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, String> {
    fun getUserByName(name: String): List<User>
    fun getUserByEmail(email: String): User?
}