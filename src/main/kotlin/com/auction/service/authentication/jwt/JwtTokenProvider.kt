package com.auction.service.authentication.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenProvider {

    private val key = Keys.hmacShaKeyFor("secretkey123-89421461297815987698724687126478jhasjhjwhjfvahfvajk".toByteArray())

    fun generateToken(authentication: Authentication): String {
        val userPrincipal = authentication.principal as UserDetails
        val now = Date()
        val expiryDate = Date(now.time + 999999999999L)
        val authorities = authentication.authorities.map { obj: GrantedAuthority ->
            obj.authority
        }.toSet()
        return Jwts.builder()
                .setSubject(userPrincipal.username)
                .claim("roles", authorities)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key)
                .compact()
    }

    fun getClaims(token: String?): Claims = Jwts.parserBuilder()
            .setSigningKey(key).build().parseClaimsJws(token).body

    fun getUsernameFromJwt(token: String?): String = getClaims(token).subject

    fun validateToken(token: String?): Boolean {
        getClaims(token)
        return true
    }
}