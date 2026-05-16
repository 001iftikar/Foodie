package com.iftikar.foodie.repository

import com.iftikar.foodie.enities.RoleEntity
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<RoleEntity, String> {
    fun findByName(name: String): RoleEntity?
}