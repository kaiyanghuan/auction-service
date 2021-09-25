package com.kotlin.lessons

class `11_Collections`

fun main(){
    val list1 : List<String> = ArrayList<String>()
    val list2 : List<String> = ArrayList()
    val list3 = listOf(1,2,3,4)

    val map1: Map<String, String> = HashMap<String, String>()
    val map2 = mapOf("key" to "value", "key2" to "value2")

    val set1 = setOf(1,2,3,4,5,1,1,2,2,4,5)
    val set2 = listOf(1,2,3,4,5,2,2,2,1,1).toSet()

    //mutable
    //in contain() isEmpty() + - filterNotNull filter{} groupBy
    // map chunked windowed
}