package com.auction.service.exceptions

import org.springframework.http.HttpStatus

class ExceptionResponse {
    lateinit var status: HttpStatus
    lateinit var errorMessage: String
    lateinit var exceptionClass: String
}
