package com.example.userservice.repository

import com.example.userservice.domain.UserEntity
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<UserEntity, Long> {
}