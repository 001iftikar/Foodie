package com.iftikar.foodie.service.impl

import com.iftikar.foodie.enities.User
import com.iftikar.foodie.repository.UserRepository
import com.iftikar.foodie.service.UserService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private var userRepository: UserRepository
) : UserService {
    override fun saveUser(user: User): User {
        val savedUser = userRepository.save(user)
        return savedUser
    }

    @Transactional
    override fun updateUser(
        user: User,
        userId: String
    ): User {
        val fetchedUser = userRepository.getReferenceById(userId)
        fetchedUser.name = user.name
        // update all fields

       return userRepository.save(fetchedUser)
    }
}