package com.example.userservice.controller

import com.example.userservice.dto.UserDto
import com.example.userservice.service.UserServiceImpl
import com.example.userservice.vo.Greeting
import com.example.userservice.vo.RequestUser
import com.example.userservice.vo.ResponseUser
import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class UserController @Autowired constructor(
    private val greeting: Greeting,
    private val userService: UserServiceImpl
) {

    @GetMapping("/health_check")
    fun status(): String {
        return "It's Working in User Service!"
    }

    @GetMapping("/welcome")
    fun welcome(): String {
        return greeting.message
    }

    @PostMapping("/users")
    fun createUser(@RequestBody user: RequestUser): ResponseEntity<ResponseUser> {
        val mapper = ModelMapper()
        mapper.configuration.matchingStrategy = MatchingStrategies.STRICT
        val userDto = mapper.map(user, UserDto::class.java)
        userService.createUser(userDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(userDto, ResponseUser::class.java))
    }
}
