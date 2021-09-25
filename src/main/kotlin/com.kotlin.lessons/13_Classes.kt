package com.kotlin.lessons

class `13_Classes`

fun main() {
    val account1 = Account(1, "12345678", AccountType.CREDIT)
    val account2 = Account2(2, "24141414", AccountType.DEBIT)
    val account3 = Account3(3, "625215524", AccountType.DEBIT)
    val (accountId, accountName, accountType) = account3 //data class
}


class Account {
    private var accountId: Int
    private var accountNumber: String
    private var accountType: AccountType

    constructor(accountId: Int, accountNumber: String, accountType: AccountType) {
        this.accountId = accountId
        this.accountNumber = accountNumber
        this.accountType = accountType
    }

}


enum class AccountType { CREDIT, DEBIT }

class Account2(
        private var accountId: Int,
        private var accountNumber: String,
        private var accountType: AccountType
)



data class Account3(
        var accountId: Int,
        var accountNumber: String,
        var accountType: AccountType
)

class genericsExample<T>(input:T) {
    init {
        println("I am getting called with the value "+input)
    }
}

//extends and implements