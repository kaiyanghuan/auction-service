package com.auction.service.controllers.responses

data class JwtAuthenticationResponse(
        val accessToken: String,
        val tokenType: String = "Bearer"
)