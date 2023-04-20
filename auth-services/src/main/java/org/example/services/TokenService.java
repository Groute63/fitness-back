package org.example.services;

public interface TokenService {
    String generateToken(String clientId);
    boolean checkToken(String token);
}
