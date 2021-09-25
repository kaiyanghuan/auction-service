package com.kotlin.lessons

class `09_IfElse`

fun main(){
    val number = 10
    if (number > 20){
        println("number $number is bigger than 20")
    } else {
        println("number $number is smaller or equals to 20")
    }

    val text = if (number > 20) "bigger" else "smaller"
    println(text)


}