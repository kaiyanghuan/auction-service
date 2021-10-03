//package com.auction.service.services
//
//import com.auction.service.exceptions.AccountNotFoundException
//import com.auction.service.exceptions.AccountStatusException
//import com.auction.service.repositories.AccountRepository
//import com.ocbc.auctionservice.entities.Account
//import org.springframework.stereotype.Service
//import java.time.Instant
//import java.util.*
//
//@Service
//class AccountService(
//        private val accountRepository: AccountRepository,
//        private val userService: UserService
//) {
//
//    fun getAccounts(userId: Int) = accountRepository.findAllByUserId(userId)
//
//    fun getAccount(id: String) : Account{
//        val accountOptional = accountRepository.findById(id)
//        if (accountOptional.isEmpty) throw AccountNotFoundException("Unable to find account $id")
//        return accountOptional.get()
//    }
//
//    fun create(account : Account): Account{
//        userService.getUser(account.userId)
//        return accountRepository.save(account)
//    }
//
//    fun update(newAccount: Account, id: String): Account{
//        val existingAccount = getAccount(id)
//        existingAccount.apply {
//            accountType = newAccount.accountType
//            currency = newAccount.currency
//            status = Account.AccountStatus.ACTIVE
//            lastActiveDate = Date()
//        }
//        return accountRepository.save(existingAccount)
//    }
//
//    fun freeze(id: String): Account{
//        val existingAccount = getAccount(id)
//        if(existingAccount.lastActiveDate.before(getNinetyDaysAgo())){
//            existingAccount.status = Account.AccountStatus.FROZEN
//            return accountRepository.save(existingAccount)
//        }
//        throw AccountStatusException("Unable to freeze account $id")
//    }
//
//    fun unfreeze(id: String): Account{
//        val existingAccount = getAccount(id)
//        if (getNinetyDaysAgo().before(existingAccount.lastActiveDate)){
//            existingAccount.status = Account.AccountStatus.ACTIVE
//            return accountRepository.save(existingAccount)
//        }
//        throw AccountStatusException("Unable to unfreeze account $id")
//    }
//
//    fun getNinetyDaysAgo(): Date {
//        val calendar = Calendar.getInstance()
//        calendar.add(Calendar.DAY_OF_YEAR, -90)
//        return calendar.time
//    }
//}