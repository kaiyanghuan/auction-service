package com.kotlin.lessons

class `03_Declarations`

fun main(){
    val text: String = "this is a string"
    val number: Number = 1
    val integer: Int = 1
    val double: Double = 1.0
    val float: Float = 1.0f
    val long: Long = 10000000000000
    val character: Char = 'A'
    val boolean: Boolean = true

    println(text)
    println(number)
    println(integer)
    println(double)
    println(float)
    println(long)
    println(character)

    var numberString = "123"
    println(numberString.toInt())
    println(numberString.toBigInteger())
    numberString = "abc"
    println(numberString.toBigIntegerOrNull())

    println(numberString is String)

    //Conversion
}