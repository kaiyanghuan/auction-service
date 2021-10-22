package com.auction.service.controllers

import com.auction.service.controllers.requests.UserRequest
import com.auction.service.controllers.responses.UserResponse
import com.auction.service.entities.User
import com.auction.service.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/api/v1/users")
class UserController(
        private val userService: UserService
) {

    @GetMapping // READ
    fun getUsers(): ResponseEntity<List<UserResponse>> = ok(userService.getUsers().map { user -> toUserResponse(user) })

    @GetMapping("/{id}") // READ
    fun getUser(@PathVariable id: Int): ResponseEntity<UserResponse> =
            ok(toUserResponse(userService.getUser(id)))

    @GetMapping("/{username}/username") // READ
    fun getUser(@PathVariable username: String): ResponseEntity<UserResponse> =
            ok(toUserResponse(userService.getUser(username)))

    @PostMapping // CREATE
    fun createUser(@RequestBody userRequest: UserRequest): ResponseEntity<UserResponse> =
            ok(toUserResponse(userService.createUser(toUser(userRequest))))

    @PutMapping("/{id}") // UPDATE
    fun updateUser(@RequestBody userRequest: UserRequest, @PathVariable id: Int): ResponseEntity<UserResponse> =
            ok(toUserResponse(userService.updateUser(toUser(userRequest), id)))

    @DeleteMapping("/{id}") // DELETE
    fun deleteUser(@PathVariable id: Int): ResponseEntity<String> {
        userService.deleteUser(id)
        return ok("ok")
    }

    private fun toUser(userRequest: UserRequest): User{
        return User().apply {
            name = userRequest.name
            username = userRequest.username
            address = userRequest.address
            password = userRequest.password
            age = userRequest.age
            roles = userRequest.roles
        }
    }

    private fun toUserResponse(user: User): UserResponse{
        return UserResponse(
                user.id!!,
                user.name,
                user.username,
                user.address,
                user.age!!,
                user.roles,
                user.permission)
    }
}