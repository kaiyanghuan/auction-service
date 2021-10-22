package com.auction.service.authentication.jwt

import java.util.*

data class JwtUser(
        val username: String,
        val name: String,
        val roles: List<String>,
        val issuedDate: Date
)