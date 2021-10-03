package com.auction.service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
open class AuctionServiceApplication

fun main(args: Array<String>){
    runApplication<AuctionServiceApplication>(*args)
}