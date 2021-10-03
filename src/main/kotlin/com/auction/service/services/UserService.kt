package com.auction.service.services

import com.auction.service.entities.User
import com.auction.service.repositories.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
        private val userRepository: UserRepository
) {

    fun getUsers(): List<User> = userRepository.findAll()

    fun getUser(username: String) : User =
            userRepository.findByUsername(username) ?: throw Exception()

//    fun findUser(id: Number) :  User{
//        return userRepository.findById(id).orElseGet(null) ?: throw Exception()
//    }

    fun getUser(id : Int) : User = userRepository.findById(id)
            .orElseGet(null) ?: throw Exception()

    fun createUser(user: User): User {
        return userRepository.save(user)
    }

    fun updateUser(newUser: User, id : Int): User{
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
        userRepository.deleteById(id)
    }
}