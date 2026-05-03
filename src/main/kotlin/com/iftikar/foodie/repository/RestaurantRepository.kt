package com.iftikar.foodie.repository

import com.iftikar.foodie.enities.Restaurant
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface RestaurantRepository : JpaRepository<Restaurant, String> {
    fun findRestaurantsByNameContainingIgnoreCase(name: String): List<Restaurant>
    fun findRestaurantsByIsOpenTrue(pageable: Pageable): Page<Restaurant>
}