package com.iftikar.foodie.service

import com.iftikar.foodie.dto.RestaurantDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface RestaurantService {
    fun save(restaurantDto: RestaurantDto): RestaurantDto
    fun update(restaurantDto: RestaurantDto, id: String): RestaurantDto
    fun deleteRestaurant(id: String)
    fun getById(id: String): RestaurantDto
    fun getAll(pageable: Pageable): Page<RestaurantDto>
    fun searchByName(name: String): List<RestaurantDto>
    fun getOpenRestaurants(pageable: Pageable): Page<RestaurantDto>
}