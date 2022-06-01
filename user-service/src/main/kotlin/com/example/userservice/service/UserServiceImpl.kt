package com.example.userservice.service

import com.example.userservice.domain.UserEntity
import com.example.userservice.dto.UserDto
import com.example.userservice.repository.UserRepository
import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserServiceImpl @Autowired constructor(
    private val userRepository: UserRepository,
    // bean으로 등록되지 않아서 Autowired되지 않는 것 -> 다른 어떤 곳에서 빈으로 정의해둬야 한다
    private val passwordEncoder: BCryptPasswordEncoder
) : UserService {
    override fun createUser(userDto: UserDto): UserDto {
        userDto.userId = UUID.randomUUID().toString()
        userDto.encryptedPwd = passwordEncoder.encode(userDto.pwd)
        val mapper = ModelMapper()
        mapper.configuration.matchingStrategy = MatchingStrategies.STRICT
        val userEntity = mapper.map(userDto, UserEntity::class.java)
        return mapper.map(userRepository.save(userEntity), userDto::class.java)
    }
}
