package com.iftikar.foodie.dto

import org.springframework.http.HttpStatus

data class ErrorResponseDto(
    val message: String,
    val status: HttpStatus
)
