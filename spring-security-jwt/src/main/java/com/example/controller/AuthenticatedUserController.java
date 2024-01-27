package com.example.controller;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth/")
public record AuthenticatedUserController(AuthenticationService authenticationService) {	
	@GetMapping("hello")
    public String sayHello(Authentication authentication) {
        return """
                Hello %s 🥳 !
                Welcome to a very secured page  😱
                """.formatted(getName(authentication));
    }

    private String getName(Authentication authentication) {
        return Optional.of(authentication)
                .filter(User.class::isInstance)
                .map(User.class::cast)
                .map(User::getUsername)
                .orElseGet(authentication::getName);
    }
}