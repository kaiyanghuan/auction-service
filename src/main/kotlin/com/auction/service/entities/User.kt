package com.auction.service.entities

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "users")
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    var id: Int? = null

    @Column(name = "name")
    lateinit var name: String

    @Column(name = "username")
    lateinit var username: String

    @Column(name = "address")
    lateinit var address: String

    @Column(name = "password")
    lateinit var password: String

    @Column(name = "age")
    var age: Int? = null

    @Column(name = "roles")
    lateinit var roles: String

    @Column(name = "permission")
    val permission: String = ""

    @Column(name = "created_by")
    val createdBy: String = "SYSTEM"

    @Column(name = "created_timestamp")
    val createdTimestamp: LocalDateTime = LocalDateTime.now()

    @Column(name = "last_modified_by")
    val lastModifiedBy: String = "SYSTEM"

    @Column(name = "last_modified_timestamp")
    val lastModifiedTimestamp: LocalDateTime = LocalDateTime.now()
}