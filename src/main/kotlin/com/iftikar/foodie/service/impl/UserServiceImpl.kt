package com.iftikar.foodie.service.impl

import com.iftikar.foodie.config.AppConstants
import com.iftikar.foodie.dto.UserDto
import com.iftikar.foodie.exception.ResourceNotFoundException
import com.iftikar.foodie.mapper.toDto
import com.iftikar.foodie.mapper.toUser
import com.iftikar.foodie.repository.RoleRepository
import com.iftikar.foodie.repository.UserRepository
import com.iftikar.foodie.service.UserService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val passwordEncoder: PasswordEncoder
) : UserService {
    override fun saveUser(userDto: UserDto): UserDto {
        val user = userDto.toUser()
        val roleGuest = roleRepository.findByName(AppConstants.ROLE_GUEST) ?: throw ResourceNotFoundException("Role not found")
        user.apply {
            password = passwordEncoder.encode(userDto.password)!!
            roleEntities.add(roleGuest)
        }
        return userRepository.save(user).toDto()
    }

    override fun updateUser(
        user: UserDto,
        userId: String
    ): UserDto {
        TODO("Not yet implemented")
    }

    override fun getUsers(pageable: Pageable): Page<UserDto> {
        return userRepository.findAll(pageable).map { it.toDto() }
    }

    override fun getByUserName(name: String): List<UserDto> {
       val users = userRepository.getUserByName(name)
           .map { it.toDto() }
        return users
    }

    override fun getByEmail(email: String): UserDto {
        val user = userRepository.getUserByEmail(email) ?: throw ResourceNotFoundException("User not found")
        return user.toDto()
    }

    override fun getById(id: String): UserDto {
        val user = userRepository.findById(id).orElseThrow {
            ResourceNotFoundException("User not found.")
        }

        return user.toDto()
    }

    override fun deleteUser(userId: String) {
        val user = userRepository.findById(userId).orElseThrow {
            ResourceNotFoundException("User not found to delete.")
        }
        userRepository.delete(user)
    }

    override fun searchUserByName(keyword: String): List<UserDto> {
        TODO("Not yet implemented")
    }

}