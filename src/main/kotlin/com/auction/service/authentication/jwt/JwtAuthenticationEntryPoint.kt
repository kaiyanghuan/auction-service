package com.auction.service.authentication.jwt

import mu.KotlinLogging
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationEntryPoint : AuthenticationEntryPoint {

    private val logger = KotlinLogging.logger {}

    override fun commence(request: HttpServletRequest,
                          response: HttpServletResponse,
                          authenticationException: AuthenticationException) {
        logger.error("Responding with authorized error. Message - ${authenticationException.message}")
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authenticationException.message)
    }
}