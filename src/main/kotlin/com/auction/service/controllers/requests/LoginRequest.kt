package com.auction.service.controllers.requests

data class LoginRequest(
        val username: String,
        val password: String
)