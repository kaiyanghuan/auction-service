package com.auction.service.entities

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class Auditable {

    @CreatedDate
    @Column(name = "created_timestamp", nullable = false)
    var createdDate: LocalDateTime =  LocalDateTime.now()

    @CreatedBy
    @Column(name = "created_by", nullable = false)
    var createdBy: String = ""

    @LastModifiedDate
    @Column(name = "last_modified_timestamp")
    var lastModifiedDate: LocalDateTime? = null

    @LastModifiedBy
    @Column(name = "last_modified_by")
    var lastModifiedBy: String? = null
}