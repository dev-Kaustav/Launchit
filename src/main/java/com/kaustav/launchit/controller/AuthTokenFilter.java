package com.kaustav.launchit.controller;

import com.kaustav.launchit.service.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filter that validates JWT tokens on each API request.
 */
@Component
public class AuthTokenFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(AuthTokenFilter.class);
    private final AuthService authService;

    public AuthTokenFilter(AuthService authService) {
        this.authService = authService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();
        log.debug("Filtering {} {}", request.getMethod(), path);
        // Allow login endpoint without token
        if ("/api/login".equals(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        String header = request.getHeader("Authorization");
        String token = (header != null && header.startsWith("Bearer "))
                ? header.substring(7) : null;
        if (token != null && authService.validate(token)) {
            log.debug("Authorized request for {}", path);
            filterChain.doFilter(request, response);
        } else {
            log.warn("Unauthorized request for {}", path);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
