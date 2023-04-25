package org.example.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.example.entity.Client;
import org.example.entity.Token;
import org.example.repository.ClientRepository;
import org.example.repository.TokenRepo;
import org.example.services.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class DefaultTokenService implements TokenService {
    @Value("${auth.jwt.secret}")
    private String secretKey;
    private final TokenRepo tokenRepo;
    private final ClientRepository clientRepository;

    public DefaultTokenService(TokenRepo tokenRepo, ClientRepository clientRepository) {
        this.tokenRepo = tokenRepo;
        this.clientRepository = clientRepository;
    }

    @Override
    public String generateToken(String clientId) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        Instant now = Instant.now();
        Instant exp = now.plus(5, ChronoUnit.DAYS);
        String jwt = JWT.create()
                .withIssuer("auth-service")
                .withAudience("fitness-app")
                .withSubject(clientId)
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(exp))
                .sign(algorithm);
        Client client = clientRepository.getById(clientId);
        Token token = new Token();
        token.setClient(client);
        token.setToken(jwt);
        client.setToken(token);
        clientRepository.save(client);
        return jwt;
    }

    @Override
    public boolean checkToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        try {
            DecodedJWT decodedJWT = verifier.verify(token);
            if (!decodedJWT.getIssuer().equals("auth-service")) {
                return false;
            }
            if (!decodedJWT.getAudience().contains("fitness-app")) {
                return false;
            }
        } catch (JWTVerificationException e) {
            return false;
        }
        return true;
    }
}
