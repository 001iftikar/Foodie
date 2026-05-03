package com.iftikar.foodie.service.impl

import com.iftikar.foodie.dto.RestaurantDto
import com.iftikar.foodie.exception.ResourceNotFoundException
import com.iftikar.foodie.mapper.toDto
import com.iftikar.foodie.mapper.toEntity
import com.iftikar.foodie.repository.RestaurantRepository
import com.iftikar.foodie.service.RestaurantService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class RestaurantServiceImpl(
    private var restaurantRepository: RestaurantRepository
) : RestaurantService {
    override fun save(restaurantDto: RestaurantDto): RestaurantDto {
        return restaurantRepository.save(restaurantDto.toEntity()).toDto()
    }

    override fun update(
        restaurantDto: RestaurantDto,
        id: String
    ): RestaurantDto {
        val fetchedRestaurant = restaurantRepository.findByIdOrNull(id) ?: throw ResourceNotFoundException("Restaurant not found")
        fetchedRestaurant.apply {
            name = restaurantDto.name
            description = restaurantDto.description
            openTime = restaurantDto.openTime
            closeTime = restaurantDto.closeTime
            isOpen = restaurantDto.isOpen
        }
        return restaurantRepository.save(fetchedRestaurant).toDto()
    }

    override fun deleteRestaurant(id: String) {
        val fetchedRestaurant = restaurantRepository.findByIdOrNull(id) ?: throw ResourceNotFoundException("Restaurant not found")
        restaurantRepository.delete(fetchedRestaurant)
    }

    override fun getById(id: String): RestaurantDto {
        val fetchedRestaurant = restaurantRepository.findByIdOrNull(id) ?: throw ResourceNotFoundException("Restaurant not found")
        return fetchedRestaurant.toDto()
    }

    override fun getAll(pageable: Pageable): Page<RestaurantDto> {
        return restaurantRepository.findAll(pageable).map { it.toDto() }
    }

    override fun searchByName(name: String): List<RestaurantDto> {
        return restaurantRepository.findRestaurantsByNameContainingIgnoreCase(name).map { it.toDto() }
    }

    override fun getOpenRestaurants(pageable: Pageable): Page<RestaurantDto> {
        return restaurantRepository.findRestaurantsByIsOpenTrue(pageable).map { it.toDto() }
    }
}