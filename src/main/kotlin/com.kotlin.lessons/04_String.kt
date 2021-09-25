package com.kotlin.lessons

class `04_String`

fun main(){
    val oldJavaString: String = "To have next line \n we need to use escape characters"
    val newKotlinString = """Now I can do next line
        without using escape characters
        the triple " is really useful!
    """.trimIndent()
    println(oldJavaString)
    println()
    println(newKotlinString)

    val string: String = "This is just a string text"
    println(string.length)
    println(string.count())
    println(string.toLowerCase())
    println(string.toUpperCase())
    println(string.lastIndex)
    println(string.drop(4))
    println(string.dropLast(4))

    val firstHalf: String = "This is "
    val secondHalf: String = " a string"
    println(firstHalf + secondHalf)
    println(firstHalf.plus(secondHalf))

    val apple1 = "Apple"
    val apple2 = "Apple"
    println(apple1.compareTo(apple2))
}