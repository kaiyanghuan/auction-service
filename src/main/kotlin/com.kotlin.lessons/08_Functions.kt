package com.kotlin.lessons

import com.kotlin.lessons.TYPE.NUMBER
import com.kotlin.lessons.TYPE.STRING

class `08_Functions`

fun main(){

    println(concatenateStrings("this is", " concatenated"))

    println(addValues(10, 20))
    println(alsoAddValues(10, 20))
    println(`weird function name`(10, 20))
    println(passInFunction(10, 20, ::addValues))
    println(passInAnotherFunction(10, 20, ::concatenateStrings))
    println(`transfer money with other people's money`(1,2))

    val lambdaFunction = {a:Int, b:Int -> a+b}
    println(lambdaFunction(10, 20))
    println(passInFunction(10, 20, lambdaFunction))
}

enum class TYPE { STRING, NUMBER }

fun concatenateStuffTogether(concatenationType: TYPE){
    when(concatenationType){
        STRING -> concatenateStrings("", "")
        NUMBER -> addValues(1, 2)
    }
}

fun concatenateStrings(a : String, b : String): String{
    return a + b
}

fun addValues(a: Int, b: Int): Int{
    return a + b
}

fun alsoAddValues(a: Int, b: Int) = a + b

fun `weird function name`(a: Int, b: Int) = a + b

fun `transfer money with other people's money`(a: Int, b: Int) = a + b

fun passInFunction(a: Int, b: Int, operation:(Int, Int) -> Int): Int{
    return operation(a,b)
}

fun passInAnotherFunction(a: Int, b: Int, operation:(String, String) -> String): String{
    return operation("Total" , " sum for ") + (a+b)
}

fun transfer(){
    val account = Account( "12489719", AccountType.DEBIT)
    `get money from account`()
    `get money from account`(account)
    `deposit money into another account`(account)
    `validate money has deposit into account`(account)
}

fun `get money from account`(account: Account = Account("12489719", AccountType.DEBIT)) = 1
fun `deposit money into another account`(otherAccount: Account) = 2
fun `validate money has deposit into account`(otherAccount: Account) = 2