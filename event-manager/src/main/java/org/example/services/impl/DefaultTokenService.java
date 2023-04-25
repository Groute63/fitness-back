package org.example.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.example.entity.Token;
import org.example.repository.TokenRepo;
import org.example.services.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultTokenService implements TokenService {
    @Value("${auth.jwt.secret}")
    private String secretKey;
    private final TokenRepo tokenRepo;

    @Override
    public boolean checkToken(String token) {
        token = token.substring(7);
        System.out.println(token);
        Optional<Token> token1 = tokenRepo.findByToken(token);
        System.out.println(token1.isPresent());
        return token1.isPresent();


        /*Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        try {
            System.out.println("aAa " + token);
            DecodedJWT decodedJWT = verifier.verify(token);
            if (!decodedJWT.getIssuer().equals("auth-service")) {
                return false;
            }
            if (!decodedJWT.getAudience().contains("fitness-app")) {
                return false;
            }
        } catch (JWTVerificationException e) {
            System.out.println(e);
            return false;
        }
        return true;
        */
    }
}
