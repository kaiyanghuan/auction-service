package com.auction.service.authentication

import com.auction.service.authentication.jwt.JwtUser
import org.springframework.security.core.context.SecurityContextHolder
import java.time.Instant
import java.util.*

object UserContext {
    fun loggedInUser(): JwtUser {
        val auth = SecurityContextHolder.getContext().authentication
        if (auth != null) {
            if (auth.principal is JwtUser) {
                return SecurityContextHolder.getContext().authentication.principal as JwtUser
            }

            throw Exception("Trying to obtain JwtUser but auth principal is ${auth.principal.javaClass.name}")
        }

        return JwtUser("SYSTEM", "SYSTEM", listOf<String>(), Date.from(Instant.now()))
    }

    fun loggedInUsername(): String = loggedInUser().username
}