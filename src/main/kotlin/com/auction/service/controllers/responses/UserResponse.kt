package com.auction.service.controllers.responses

data class UserResponse(
        val id: Int,
        val name: String,
        val username: String,
        val address: String,
        val age: Int,
        val roles: String,
        val permission: String
)