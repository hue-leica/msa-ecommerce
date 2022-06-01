package com.example.userservice.domain

import com.example.userservice.annotation.NoArg
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user")
@NoArg
data class UserEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    @Column(nullable = false, length = 50, unique = true)
    var email: String,
    @Column(nullable = false, length = 50)
    var name: String,
    @Column(nullable = false, unique = true)
    var userId: String,
    @Column(nullable = false)
    var pwd: String,
    @Column(nullable = false)
    var encryptedPwd: String
)
