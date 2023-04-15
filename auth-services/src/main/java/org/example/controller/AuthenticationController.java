package org.example.controller;

import org.example.entity.dto.UserDto;
import org.example.services.AuthenticationService;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public Boolean authorization(@RequestBody UserDto user) {
        return authenticationService.authorization(user);
    }

    @GetMapping
    public int authorization() {
        return 1;
    }
}
