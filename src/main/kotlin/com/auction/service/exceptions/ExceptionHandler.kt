package com.auction.service.exceptions

import io.jsonwebtoken.JwtException
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ExceptionHandler {

    private val logger = KotlinLogging.logger {}

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessException::class)
    @ResponseBody
    fun handleBusinessExceptions(ex: BusinessException): ExceptionResponse {
        logger.error(ex.localizedMessage)
        return createExceptionResponse(ex, HttpStatus.BAD_REQUEST)
    }

    private fun createExceptionResponse(ex: Exception, status: HttpStatus): ExceptionResponse {
        return ExceptionResponse().apply {
            this.status = status
            errorMessage = ex.localizedMessage
            exceptionClass = ex.javaClass.canonicalName
        }
    }
}
