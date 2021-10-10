package com.auction.service.authentication.jwt

import com.auction.service.services.UserService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import javax.servlet.http.HttpServletRequest

class JwtToSpringAuthenticationBuilder(
        private val jwtTokenProvider: JwtTokenProvider,
        private val userService: UserService
) {

    private lateinit var token: String
    private lateinit var request: HttpServletRequest

    fun withToken(token: String): JwtToSpringAuthenticationBuilder {
        this.token = token
        return this
    }

    fun withHttpServletRequest(request: HttpServletRequest): JwtToSpringAuthenticationBuilder {
        this.request = request
        return this
    }

    fun build(): Authentication {
        val claims = jwtTokenProvider.getClaims(token)
        val username = claims.subject
        val roles = claims["roles"] as List<String>
        val authorities = roles.map { role -> SimpleGrantedAuthority(role) }

        val user = userService.getUser(username)

        val userDetails = JwtUser(username, user.name, roles, claims.issuedAt)
        val authentication = UsernamePasswordAuthenticationToken(userDetails, user.password, authorities)

        authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
        return authentication
    }
}