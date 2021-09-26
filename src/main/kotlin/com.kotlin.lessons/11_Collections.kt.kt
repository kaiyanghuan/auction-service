package com.kotlin.lessons

import com.ocbc.auctionservice.entities.User
import com.ocbc.auctionservice.services.UserService

class `11_Collections`

fun main(){
    val list1 : List<String> = ArrayList<String>()
    val list2 : List<String> = ArrayList()
    val list3 = listOf(1,2,3,4)

    val map1: Map<String, String> = HashMap<String, String>()
    val map2 = mapOf("key" to "value", "key2" to "value2")

    val set1 = setOf(1,2,3,4,5,1,1,2,2,4,5)
    val set2 = listOf(1,2,3,4,5,2,2,2,1,1).toSet()

    val mutableList = mutableListOf<String>("A", "B", "C")
    mutableList.add("D")
    mutableList.add("E")
    mutableList[4]

    val mutableMap = mutableMapOf("A" to 1, "B" to 2)
    mutableMap["C"] = 3
    mutableMap["A"]

    val mutableSet = mutableSetOf<Int>(1,2,3,4)
    mutableSet.add(3)

//    val kaiyang = User()
//    kaiyang.address = "124 Punggol"
//    kaiyang.age = 30
//    kaiyang.name = "kaiyang"
//
//    val userService: UserService = UserService()
//    val existingUser = userService.getUser(1)
//
//    val userSet = mutableSetOf<User>(kaiyang, existingUser)
//    userSet.add(kaiyang)
//    val secondUserSet = mutableSetOf<User>(userService.getUser(1), userService.getUser(2))
//
//    println(1 !in list3)
//    println(list3.contains(1))
//    println(userSet.containsAll(secondUserSet))

    val listA = listOf("A", "B", "C", "D", "E", null, null)
    val listB = listOf("A", "D")
    val listC = listA + listB

    println(listA + listB)
    println(listA - listB)

    var x: Int? = 1
    var y: Number? = 1
    var account : Account? = Account("", AccountType.DEBIT)

    x = null
    y = null

    val newAccount: Account = account ?: Account("", AccountType.CREDIT)
    newAccount.function()

    val nonNullableAccount = account!!

//    val accounts = listOf(account, newAccount, Account( "", AccountType.DEBIT))
    val users = listOf(User(), User(), User())

    println(listA.filterNotNull())
    println(listA.filter { x -> x == "A" })
    println(listA.map { x -> x })

//    val names = users.filter{ user -> user.age > 30 }
//            .map { user -> user.name }
//
//    val totalSum = users.filter { user -> user.roles == "some role" }
//            .map { user -> user.age }
//
//    val userGroups: Map<String, List<User>> = users.groupBy { user -> user.roles }
//    userGroups.map { (role, users) -> users }
//
//    fun getSpecificUser(role: String, index : Int) {
//        val specificUser = userGroups[role]?.get(index) ?: throw Exception("")
//    }

    println(listOf(1,2,3,4,5,6,7,8,9,10,11,12).chunked(3))
    println(listOf(1,2,3,4,5,6,7,8,9,10,11,12).windowed(3, 2))

    //mutable
    //in contain() isEmpty() + - filterNotNull filter{} groupBy
    // map chunked windowed
}