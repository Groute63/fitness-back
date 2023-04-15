package org.example.services;

import org.example.entity.dto.UserDto;
import org.example.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }

    public Boolean authorization(UserDto user) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                user.getLogin(), user.getPassword()
        ));
        return userRepository.existsUserByLoginAndPassword(user.getLogin(), user.getPassword());
    }
}
