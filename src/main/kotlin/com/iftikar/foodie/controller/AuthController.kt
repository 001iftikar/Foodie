package com.iftikar.foodie.controller

import com.iftikar.foodie.payload.JwtResponse
import com.iftikar.foodie.payload.LoginRequest
import com.iftikar.foodie.payload.RefreshTokenRequest
import com.iftikar.foodie.security.JwtService
import com.iftikar.foodie.service.UserService
import org.springframework.http.HttpStatus
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
    private val jwtService: JwtService,
    private val userService: UserService
) {
    @PostMapping("/login")
    fun login(
        @RequestBody loginRequest: LoginRequest
    ): ResponseEntity<Any> {
        val authentication = UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password)
        authenticationManager.authenticate(authentication)
        val jwtToken = jwtService.generateToken(loginRequest.username, true)
        val refreshToken = jwtService.generateToken(loginRequest.username, false)
        val userDetails = userDetailsService.loadUserByUsername(loginRequest.username)
        val user = userService.getByEmail(userDetails.username)
        val response = JwtResponse(jwtToken, refreshToken, user)
        return ResponseEntity.ok(response)
    }

    /**
     * Refresh token generate via request
     */
    @PostMapping("/refresh-token")
    fun refreshToken(
        @RequestBody refreshTokenRequest: RefreshTokenRequest
    ): ResponseEntity<Any> {
        if (!jwtService.isAccessToken(refreshTokenRequest.refreshToken)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not a refresh token`")
        }
        if (jwtService.validateToken(refreshTokenRequest.refreshToken)) {
            val userName = jwtService.getUsername(refreshTokenRequest.refreshToken)
            val userDto = userService.getByEmail(userName)
            val accessToken = jwtService.generateToken(userDto.email, true)
            val newRefreshToken = jwtService.generateToken(userDto.email, false)
            val jwtResponse = JwtResponse(accessToken, newRefreshToken, userDto)
            return ResponseEntity.status(HttpStatus.OK).body(jwtResponse)
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token")
        }
    }
}



























