package com.auction.service.entities

import javax.persistence.*

@Entity
@Table(name = "users")
class User : Auditable() {

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
}