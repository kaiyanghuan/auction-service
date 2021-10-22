package com.auction.service.exceptions

open class BusinessException(message: String) : RuntimeException(message)

class UserNotFoundException(message: String): BusinessException(message)
class UserAlreadyExistException(message: String): BusinessException(message)

class AccountNotFoundException(message: String): BusinessException(message)
class AccountAlreadyExistException(message: String): BusinessException(message)
class AccountNotEnoughValueException(message: String): BusinessException(message)
class AccountStatusException(message: String): BusinessException(message)