package com.iftikar.foodie.controller

import com.iftikar.foodie.dto.UserDto
import com.iftikar.foodie.service.UserService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UserController(private val userService: UserService) {

    @PostMapping
    fun create(
        @RequestBody userDto: UserDto
    ): ResponseEntity<UserDto> {
       val user = userService.saveUser(userDto)
        return ResponseEntity(user, HttpStatus.CREATED)
    }

    @GetMapping
    fun getAllUsers(
        @RequestParam page: Int,
        @RequestParam size: Int = 10,
        @RequestParam(required = false, defaultValue = "name") sortBy: String,
        @RequestParam(required = false, defaultValue = "asc") sortDir: String
    ): ResponseEntity<Page<UserDto>> {
        val sort = if (sortDir.equals("desc", true)) Sort.by(sortBy).descending() else Sort.by(sortBy).ascending()
        val pageable = PageRequest.of(page, size, sort)
        val users = userService.getUsers(pageable)
        return ResponseEntity.ok(users)
    }

    @GetMapping("/{userId}")
    fun getUserById(
        @PathVariable userId: String
    ): ResponseEntity<UserDto> {
        val user = userService.getById(userId)
        return ResponseEntity.ok(user)
    }
}