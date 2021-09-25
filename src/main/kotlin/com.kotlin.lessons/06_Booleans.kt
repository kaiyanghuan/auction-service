package com.kotlin.lessons

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
}