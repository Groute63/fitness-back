package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.dto.ErrorResponse;
import org.example.entity.dto.TokenResponse;
import org.example.entity.dto.UserDto;
import org.example.services.ClientService;
import org.example.services.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final ClientService clientService;
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody UserDto user) throws LoginException {
        clientService.register(user.getLogin(), user.getPassword());
        return ResponseEntity.ok("Registered");
    }

    @PostMapping("/token")
    public TokenResponse getToken(@RequestBody UserDto user) throws LoginException {
        clientService.checkCredentials(user.getLogin(), user.getPassword());
        return new TokenResponse(tokenService.generateToken(user.getLogin()));
    }

    @ExceptionHandler({LoginException.class})
    public ResponseEntity<ErrorResponse> handleUserRegistrationException(LoginException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(ex.getMessage()));
    }
}
