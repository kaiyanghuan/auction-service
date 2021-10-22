package com.kotlin.lessons

class `13_Classes`

fun main() {
    val account1 = Account("12345678", AccountType.CREDIT)
    println(account1.accountId)

    val account2 = Account2(2, "24141414", AccountType.DEBIT)
    val account3 = Account3(3, "625215524", AccountType.DEBIT)
    val (accountId, accountName, accountType) = account3 //data class

    val genericClass = genericsExample<Account>(account1)

    val person = Person()
    person.walk()
    person.work()

    val adult = Adult()
    adult.eat()
    adult.sleep()
    adult.work()

    account2.accountNumber = "1241241897"
    account2.accountType = AccountType.DEBIT

    account2.apply {
        this.accountNumber = "21414"
        this.accountId = 4
        this.accountType = AccountType.CREDIT
    }

    AccountType.valueOf("DEBIT")
    val dataAccount = Account3(1, "412907194", AccountType.DEBIT)
    dataAccount.accountNumber
    dataAccount.accountId
    dataAccount.accountType
}

data class Account3(
        var accountId: Int,
        var accountNumber: String,
        var accountType: AccountType)

enum class AccountType { CREDIT, DEBIT }

class Account {
    var accountId: Int
    var accountNumber: String
    var accountType: AccountType

    constructor(accountNumber: String, accountType: AccountType) {
        this.accountNumber = accountNumber
        this.accountType = accountType
    }

    init {
        println("Init runs when constructor is called")
        accountId = 1
    }

    fun function(): Int {
        return 1
    }
}

class Account2(
        var accountId: Int,
        var accountNumber: String,
        var accountType: AccountType
) {

}


class genericsExample<T>(input: T) {
    init {
        println("I am getting called with the value " + input)
    }
}

interface Human{
    fun eat()
    fun sleep()
}

open class Employee{
    fun work(): String{
        return "Work is tiring"
    }
}

class Baby : Human {
    override fun eat() {
        println("Bleh bleh")
    }

    override fun sleep() {
        println("Bleh bleh bleh")
    }
}

class Person : Employee() {
    fun walk(){
        println("I am walking")
    }
}

class Adult : Employee(), Human {
    override fun eat() {
        println("I am going to eat")
    }

    override fun sleep() {
        println("I am going to sleep")
    }
}


//extends and implements