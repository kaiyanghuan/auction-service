package com.auction.service.logging

import mu.KotlinLogging
import org.slf4j.MDC
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class LoggingDiagnosticFilter : OncePerRequestFilter() {

    private val logger = KotlinLogging.logger {}

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain
    ) {
        MDC.clear()
        val startTime = System.currentTimeMillis()
        try {
            MDC.putCloseable("requestId", getRequestId(request))
            logger.info("[START] request ${request.requestURI}")
            filterChain.doFilter(request, response)
        } finally {
            val endTime = System.currentTimeMillis()
            val duration = endTime - startTime
            MDC.putCloseable("responseCode", response.status.toString() + "")
            MDC.putCloseable("responseTimeMillis", duration.toString() + "")
            logger.info("[COMPLETE] Request ${request.requestURI} in $duration duration")
        }
    }

    private fun getRequestId(request: HttpServletRequest): String {
        val header = request.getHeader("X-B3-TraceId")
        return header ?: UUID.randomUUID().toString()
    }
}