package com.helmes.citymanagement.rest;

import com.helmes.citymanagement.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class AuthRestServiceImpl implements AuthRestService{
    private static final Logger LOG = LoggerFactory.getLogger(AuthRestServiceImpl.class);

    private final TokenService tokenService;

    public AuthRestServiceImpl(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public String token(Authentication authentication) {
        String token = tokenService.generateToken(authentication);
        LOG.debug("Token generated for user '{}', {}", authentication.getName(), token);
        return token;
    }
}
