package com.iftikar.foodie.controller

import com.iftikar.foodie.dto.RestaurantDto
import com.iftikar.foodie.service.RestaurantService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/restaurants")
class RestaurantController(private val restaurantService: RestaurantService) {
    @PostMapping
    fun save(
        @RequestBody restaurantDto: RestaurantDto
    ): ResponseEntity<RestaurantDto> {
        val savedRest = restaurantService.save(restaurantDto)
        return ResponseEntity(savedRest, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: String,
        @RequestBody restaurantDto: RestaurantDto
    ): ResponseEntity<RestaurantDto> {
        val updatedRest = restaurantService.update(restaurantDto, id)
        return ResponseEntity(updatedRest, HttpStatus.CREATED)
    }

    @GetMapping
    fun getAll(
        @RequestParam page: Int,
        @RequestParam size: Int = 10
    ): ResponseEntity<Page<RestaurantDto>> {
        val pageable = PageRequest.of(page, size)
        val restaurants = restaurantService.getAll(pageable)
        return ResponseEntity(restaurants, HttpStatus.OK)
    }
}






















