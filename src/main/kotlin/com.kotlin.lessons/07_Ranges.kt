package com.kotlin.lessons

class `07_Ranges`

fun main(){
    1.rangeTo(4)
    1..2
    'a'..'z'
    4 downTo 1
    1..20 step 4
    (1..5).reversed()
    1 until 10

    val intArray = intArrayOf(1,2,3,4,5,6,7,8)

    for(i in intArray.indices){
        print(intArray[i])
    }


}