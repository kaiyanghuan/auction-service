package com.auction.service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class AuctionServiceApplication

fun main(args: Array<String>){
    runApplication<AuctionServiceApplication>(*args)
}