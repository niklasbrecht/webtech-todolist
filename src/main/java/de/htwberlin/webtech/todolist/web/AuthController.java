package de.htwberlin.webtech.todolist.web;

import de.htwberlin.webtech.todolist.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/api/v2/auth")
    public String token(Authentication authentication) {
        String token = tokenService.generateToken(authentication);
        return token;
    }

}
