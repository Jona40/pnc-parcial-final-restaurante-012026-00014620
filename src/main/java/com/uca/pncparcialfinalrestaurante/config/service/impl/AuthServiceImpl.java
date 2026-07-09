package com.uca.pncparcialfinalrestaurante.config.service.impl;

import com.uca.pncparcialfinalrestaurante.config.domain.dto.JwtAuthResponse;
import com.uca.pncparcialfinalrestaurante.config.domain.dto.LoginRequest;
import com.uca.pncparcialfinalrestaurante.config.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    public JwtAuthResponse login(LoginRequest loginRequest) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);

        String accessToken = jwtTokenProvider.generateAccessToken(auth);
        String refreshToken = jwtTokenProvider.generateRefreshToken(auth.getName());

        return JwtAuthResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
    }
}