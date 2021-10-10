package com.auction.service.authentication.jwt

import com.auction.service.authentication.UserContext
import com.auction.service.services.UserService
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import org.slf4j.MDC
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.RequestMatcher
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtTokenAuthenticationFilter(
        private val jwtTokenProvider: JwtTokenProvider,
        private val userService: UserService) : OncePerRequestFilter() {

    private var requestMatcher: RequestMatcher = AntPathRequestMatcher("/api/v1/auth/**")

    override fun doFilterInternal(request: HttpServletRequest,
                                  response: HttpServletResponse,
                                  filterChain: FilterChain) {
        try {
            if (hasNoAuthorization(request)) {
                if (shouldNotFilter(request)) {
                    filterChain.doFilter(request, response)
                    return
                }
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized user")
                return
            }

            val token = generateToken(request)
            if (!checkAuthenticationAndValidity(token)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized user")
                return
            }

            val authentication: Authentication = JwtToSpringAuthenticationBuilder(jwtTokenProvider, userService)
                    .withToken(token)
                    .withHttpServletRequest(request)
                    .build()
            SecurityContextHolder.getContext().authentication = authentication
            MDC.put("username", UserContext.loggedInUsername())
            filterChain.doFilter(request, response)
        } catch (e: Exception) {
            handleException(e, response)
        } finally {
            SecurityContextHolder.clearContext()
        }
    }

    override fun shouldNotFilter(request: HttpServletRequest) = requestMatcher.matches(request)

    private fun hasNoAuthorization(request: HttpServletRequest) = !hasAuthenticationHeader(request, AUTHORIZATION)

    private fun hasAuthenticationHeader(request: HttpServletRequest, headerKey: String): Boolean {
        val header = request.getHeader(headerKey)
        return header != null && header.startsWith("Bearer ")
    }

    private fun generateToken(request: HttpServletRequest): String {
        if (hasAuthenticationHeader(request, AUTHORIZATION)) return request.getHeader(AUTHORIZATION).replace("Bearer ", "")
        return ""
    }

    private fun checkAuthenticationAndValidity(token: String) = jwtTokenProvider.validateToken(token)

    private fun handleException(e: Exception, response: HttpServletResponse) {
        when (e) {
            is ExpiredJwtException -> response.sendError(HttpServletResponse.SC_FORBIDDEN, "Token is not valid anymore")
            is UnsupportedJwtException -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unsupported Jwt Format")
            is MalformedJwtException -> response.sendError(HttpServletResponse.SC_FORBIDDEN, "Token is malformed")
        }
        throw e
    }

}