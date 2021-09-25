package com.kotlin.lessons

class `08_Functions`

fun main(){

    println(addValues(10, 20))
    println(alsoAddValues(10, 20))
    println(`weird function name`(10, 20))
    println(passInFunction(10, 20, ::addValues))

    val lambdaFunction = {a:Int, b:Int -> a+b}
    println(lambdaFunction(10, 20))
}

fun addValues(a: Int, b: Int): Int{
    return a + b
}

fun alsoAddValues(a: Int, b: Int) = a + b

fun `weird function name`(a: Int, b: Int) = a + b

fun passInFunction(a: Int, b: Int, operation:(Int, Int) -> Int): Int{
    return operation(a,b)
}