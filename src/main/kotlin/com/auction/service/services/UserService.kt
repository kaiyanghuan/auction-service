package com.auction.service.services

import com.auction.service.entities.User
import com.auction.service.exceptions.UserAlreadyExistException
import com.auction.service.exceptions.UserNotFoundException
import com.auction.service.repositories.UserRepository
import lombok.extern.slf4j.Slf4j
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class UserService(
        private val userRepository: UserRepository
) {
    private val logger = KotlinLogging.logger {}

    fun getUsers(): List<User> {
        logger.info("Retrieving all users")
        return userRepository.findAll()
    }

    fun getUser(username: String): User {
        logger.info("Retrieving user $username")
        return userRepository.findByUsername(username) ?: throw UserNotFoundException("Unable to find user $username")
    }

//    fun findUser(id: Number) :  User{
//        return userRepository.findById(id).orElseGet(null) ?: throw Exception()
//    }

    fun getUser(id: Int): User {
        logger.info("Retrieving user $id")
        val optionalUser = userRepository.findById(id)
        if (optionalUser.isEmpty) throw UserNotFoundException("Unable to find user $id")
        return optionalUser.get()
    }

    fun createUser(user: User): User {
        logger.info("Create user ${user.name}")
        val existingUser = userRepository.findByUsername(user.username)
        if (existingUser != null) throw UserAlreadyExistException("Unable to create user ${user.name} due to username ${user.username} has already been taken.")
        return userRepository.save(user)
    }

    fun updateUser(newUser: User, id: Int): User {
        logger.info("Updating user ${newUser.name}")
        val existingUser = getUser(id)
        existingUser.apply {
            name = newUser.name
            username = newUser.username
            address = newUser.address
            password = newUser.password
            age = newUser.age
            roles = newUser.roles
        }
        return userRepository.save(existingUser)
    }

    fun deleteUser(id: Int) {
        logger.info("Deleting user $id")
        if (!userRepository.existsById(id)) throw UserNotFoundException("Unable to find user $id")
        userRepository.deleteById(id)
    }
}