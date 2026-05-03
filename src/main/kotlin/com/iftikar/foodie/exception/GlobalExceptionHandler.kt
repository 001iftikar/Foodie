package com.iftikar.foodie.exception

import com.iftikar.foodie.dto.ErrorResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleException(ex: ResourceNotFoundException): ResponseEntity<ErrorResponseDto> {
        val error = ErrorResponseDto(
            message = ex.message ?: "Unknown Error",
            status = HttpStatus.NOT_FOUND
        )

        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }
}