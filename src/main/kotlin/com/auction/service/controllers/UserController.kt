package com.auction.service.controllers

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
    fun getUsers(): ResponseEntity<List<User>> = ok(userService.getUsers())

    @GetMapping("/{id}") // READ
    fun getUser(@PathVariable id: Int): ResponseEntity<User> =
            ok(userService.getUser(id))

    @GetMapping("/{username}/username") // READ
    fun getUser(@PathVariable username: String): ResponseEntity<User> =
            ok(userService.getUser(username))

    @PostMapping // CREATE
    fun createUser(@RequestBody user: User): ResponseEntity<User> =
            ok(userService.createUser(user))

    @PutMapping("/{id}") // UPDATE
    fun updateUser(@RequestBody user: User, @PathVariable id: Int): ResponseEntity<User> =
            ok(userService.updateUser(user, id))

    @DeleteMapping("/{id}") // DELETE
    fun deleteUser(@PathVariable id: Int): ResponseEntity<String> {
        userService.deleteUser(id)
        return ok("ok")
    }
}