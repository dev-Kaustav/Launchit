package com.kaustav.launchit.controller;

import com.kaustav.launchit.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for authentication operations.
 */
@RestController
@RequestMapping("/api")
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Login endpoint that returns a JWT token on success.
     */
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request) {
        log.info("POST /api/login for {}", request.username);
        String token = authService.login(request.username, request.password);
        if (token == null) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(new TokenResponse(token));
    }

    /** Request body for login. */
    public record LoginRequest(String username, String password) {}

    /** Simple token response body. */
    public record TokenResponse(String token) {}
}
