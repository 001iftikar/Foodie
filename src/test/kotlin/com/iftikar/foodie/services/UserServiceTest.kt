package com.iftikar.foodie.services

import com.iftikar.foodie.enities.Restaurant
import com.iftikar.foodie.enities.Role
import com.iftikar.foodie.enities.User
import com.iftikar.foodie.repository.UserRepository
import com.iftikar.foodie.service.UserService
import com.iftikar.foodie.service.impl.UserServiceImpl
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class UserServiceTest {

    @Mock // Creates a fake version of your repository
    lateinit var userRepository: UserRepository

    @InjectMocks // Plugs the fake repository into your real service
    lateinit var userService: UserServiceImpl

}