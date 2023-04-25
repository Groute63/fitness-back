package org.example.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.services.TokenService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//@Component
//@RequiredArgsConstructor
public class AuthorizationFilter{ //extends OncePerRequestFilter {

    /* private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Прохожу фильтр");
        filterChain.doFilter(request, response);
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || authHeader.isBlank())
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        else if (!checkAuthorization(authHeader))
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        else
            filterChain.doFilter(request, response);
    }

    private boolean checkAuthorization(String auth) {
        if (!auth.startsWith("Bearer "))
            return false;
        String token = auth.substring(7);
        return tokenService.checkToken(token);
    }*/
}
