package com.example.userservice.vo

import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class RequestUser(
    @NotNull(message = "Email Cannot be null")
    @Size(min = 2, message = "Email not be less than two characters")
    @Email
    val email: String,
    @NotNull(message = "Name Cannot be null")
    val name: String,
    @NotNull(message = "Password Cannot be null")
    val pwd: String
)
