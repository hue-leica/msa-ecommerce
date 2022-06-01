package com.example.firstservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/first-service")
@RequiredArgsConstructor
public class FirstServiceController {

    final Environment env;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the First service";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("first-request") String header) {
        log.info(header);
        return "First Service > message";
    }

    @GetMapping("/check")
    public String check(HttpServletRequest request) {
        log.info("Server port = {}", request.getServerPort());
        return String.format("First Service > check > Port = %s", env.getProperty("local.server.port"));
    }
}
