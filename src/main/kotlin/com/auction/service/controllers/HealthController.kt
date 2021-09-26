package com.auction.service.controllers

import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/api/v1/health")
class HealthController{

    @GetMapping
    fun health(): ResponseEntity<String> = ok("I am here!")
}