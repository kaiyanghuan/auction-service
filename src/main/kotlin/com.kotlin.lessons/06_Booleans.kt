package com.kotlin.lessons

import com.ocbc.auctionservice.entities.User

class `06_Booleans`

fun main(){

    val xBoolean: Boolean = true
    val yBoolean: Boolean = false

    println(xBoolean || yBoolean)
    println(xBoolean && yBoolean)
    println(!xBoolean)
    println(!yBoolean)

    println(xBoolean.or(yBoolean))
    println(xBoolean.and(yBoolean))
    println(xBoolean.not())
    println(yBoolean.not())

    println("Text" == "Text")
    println(User() == User())
}