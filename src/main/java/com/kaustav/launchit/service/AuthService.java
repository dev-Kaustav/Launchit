package com.kaustav.launchit.service;

import com.kaustav.launchit.db.User;
import com.kaustav.launchit.db.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.JwtException;
import java.nio.charset.StandardCharsets;

/**
 * Service providing basic JWT-based authentication.
 */
@Service
public class AuthService {
    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    private final byte[] secret;

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository,
                       @Value("${jwt.secret:changeit-secret-key}") String secret) {
        this.userRepository = userRepository;
        this.secret = secret.getBytes(StandardCharsets.UTF_8);
    }

    /**
     * Authenticate a username/password pair. Returns a token if valid.
     */
    public String login(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(u -> u.getPassword().equals(password))
                .map(u -> {
                    String token = Jwts.builder()
                            .setSubject(Integer.toString(u.getId()))
                            .signWith(Keys.hmacShaKeyFor(secret), SignatureAlgorithm.HS256)
                            .compact();
                    log.info("Generated JWT for user {}", username);
                    return token;
                })
                .orElse(null);
    }

    /**
     * Validate a token.
     */
    public boolean validate(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secret))
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            log.warn("Invalid JWT: {}", e.getMessage());
            return false;
        }
    }
}
