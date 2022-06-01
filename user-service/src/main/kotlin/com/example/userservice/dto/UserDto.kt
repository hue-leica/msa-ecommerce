package com.example.userservice.dto

import com.example.userservice.annotation.NoArg
import java.util.Date

@NoArg
data class UserDto(
    var userId: String,
    var email: String,
    var name: String,
    var pwd: String,
    var createdAt: Date,
    var encryptedPwd: String
)
