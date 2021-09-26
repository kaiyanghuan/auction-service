package com.auction.service.entities

import java.util.*

class User {
    var userId: UUID = UUID.randomUUID()
    lateinit var username: String
    lateinit var address: String
    lateinit var password: String
    lateinit var age: Number
}