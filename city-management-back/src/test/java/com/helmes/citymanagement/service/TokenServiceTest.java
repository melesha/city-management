package com.helmes.citymanagement.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TokenServiceTest {

    public static final String TOKEN = "token";
    public static final String ADMIN = "admin";
    @MockBean
    private JwtEncoder encoder;

    @Mock
    private Jwt jwt;

    @MockBean
    Authentication authentication;

    @Autowired
    private TokenService tokenService;

    @Test
    void getToken() {
        Mockito.when(encoder.encode(Mockito.any())).thenReturn(jwt);
        Mockito.when(jwt.getTokenValue()).thenReturn(TOKEN);
        Authentication auth = new UsernamePasswordAuthenticationToken(ADMIN, ADMIN);
        String token = tokenService.generateToken(auth);
        assertEquals(TOKEN, token);
    }

}
