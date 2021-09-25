package com.kotlin.lessons

class `12_Iterations`

fun main(){

    val list = listOf(1,2,3,4,5,6,7,8)
    for(number in list){
        print(number)
    }
    println()

    for(number in 1..8){
        print(number)
    }
    println()

    for(number in 10 downTo 2 step 3) {
        print(number)
    }
    println()

    var number = 10
    while(number > 1){
        print(number)
        number--
    }
    println()


}