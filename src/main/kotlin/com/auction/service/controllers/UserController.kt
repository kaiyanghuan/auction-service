package com.auction.service.controllers

import com.auction.service.services.UserService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/api/v1/users")
class UserController(
        private val userService: UserService
) {

    @PostMapping
    fun createUser() {
        userService.createUser();
    }
}