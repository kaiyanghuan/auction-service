package com.auction.service.authentication.jwt

import com.auction.service.services.UserService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class UserPrincipalDetailsService(
        private val userService: UserService
): UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userService.getUser(username)
        return UserPrincipal(user)
    }

}