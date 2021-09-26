package com.kotlin.lessons

class `05_Arrays`

fun main(){
//    val numbers = intArrayOf(1,2,3,4)
//    println(numbers.getOrElse(10) { 0 } )
//    println(numbers.getOrNull(10) )
//
//    numbers[0]
//
//    arrayOf("String", 1, true)
//    booleanArrayOf()
//    emptyArray<String>()



    val numbers = intArrayOf(1,2,3,4,5)
    val numbers2 = intArrayOf(4,5,6,7,8)
    val strings = arrayOf("A", "B", "C")
    val booleans = booleanArrayOf(true , false, true, false)
    val emptyArrays = emptyArray<String>()

    numbers[0]
    numbers.get(0)
    numbers.size
    numbers.getOrNull(10)
    println(numbers.getOrElse(10) { 0 })
    println(1 in numbers)
    println(10 in numbers)
    println(booleans.distinct())
    println(booleans.isEmpty())
    println(emptyArrays.isNotEmpty())
    print(numbers.filter { num -> num in numbers2 })
}