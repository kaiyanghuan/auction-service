package com.kotlin.lessons

class `10_When`

fun main(){
    val day = 2

    val result = when(day) {
        1 -> "Monday"
        2 -> "Tuesday"
        3 -> "Wednesday"
        4 -> "Thursday"
        5 -> "Friday"
        6 -> "Saturday"
        7 -> "Sunday"
        else -> "Invalid day."
    }
    println(result)


    when (day) {
        1, 2, 3, 4, 5 -> println("Weekday")
        6,7 -> println("Weekend")
        else -> println("Invalid day")
    }


    when (day) {
        in 1..5 -> println("Weekday")
        else -> println("Weekend")
    }
}