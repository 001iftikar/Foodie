package com.iftikar.foodie.controller

import com.iftikar.foodie.payload.JwtResponse
import com.iftikar.foodie.payload.LoginRequest
import com.iftikar.foodie.security.JwtService
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: UserDetailsService,
    private val jwtService: JwtService
) {

    @PostMapping("/login")
    fun login(
        @RequestBody loginRequest: LoginRequest
    ): ResponseEntity<Any> {
        val authentication = UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password)
        authenticationManager.authenticate(authentication)
        val token = jwtService.generateToken(loginRequest.username)
        val userDetails = userDetailsService.loadUserByUsername(loginRequest.username)
        val response = JwtResponse(token)
        return ResponseEntity.ok(response.token)
    }
}