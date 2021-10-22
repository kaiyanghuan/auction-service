package com.auction.service.controllers.requests

data class UserRequest(
        val name: String,
        val username: String,
        val password: String,
        val address: String,
        val age: Int,
        val roles: String
)