package com.iftikar.foodie.service.impl

import com.iftikar.foodie.exception.ResourceNotFoundException
import com.iftikar.foodie.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
       val user = userRepository.getUserByEmail(username) ?: throw ResourceNotFoundException("Email is not registered")
        val userDetails = com.iftikar.foodie.security.UserDetails(user)
        return userDetails
    }
}