package com.example.userservice.vo

import com.example.userservice.annotation.NoArg

@NoArg
data class ResponseUser(
    var email: String,
    var name: String,
    var userId: String
)
