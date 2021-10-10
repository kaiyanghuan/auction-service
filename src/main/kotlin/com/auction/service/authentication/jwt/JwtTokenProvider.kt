package com.auction.service.authentication.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenProvider(
        @Value("\${app.auth.jwtSecret}") val jwtSecret: String,
        @Value("\${app.auth.jwtExpirationInMs}") val jwtExpirationInMs: Long
) {
    private val key = Keys.hmacShaKeyFor(jwtSecret.toByteArray())

    fun generateToken(authentication: Authentication): String {
        val userPrincipal = authentication.principal as UserDetails
        val now = Date()
        val expiryDate = Date(now.time + jwtExpirationInMs)
        val authorities = authentication.authorities.map { grantedAuthority: GrantedAuthority ->
            grantedAuthority.authority
        }.toSet()
        return Jwts.builder()
                .setSubject(userPrincipal.username)
                .claim("roles", authorities)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key)
                .compact()
    }

    fun getClaims(token: String): Claims {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).body
    }

    fun getUsernameFromJwt(token: String): String = getClaims(token).subject

    fun validateToken(token: String): Boolean {
        val claim = getClaims(token)
        if (claim.expiration.after(Date())) return true
        return false
    }

}