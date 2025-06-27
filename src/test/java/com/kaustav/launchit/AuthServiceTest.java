package com.kaustav.launchit;

import com.kaustav.launchit.db.User;
import com.kaustav.launchit.db.UserRepository;
import com.kaustav.launchit.service.AuthService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class AuthServiceTest {
    @Test
    void loginReturnsTokenForValidCredentials() {
        UserRepository repo = Mockito.mock(UserRepository.class);
        User user = new User();
        user.setId(1);
        user.setUsername("john");
        user.setPassword("pass");
        Mockito.when(repo.findByUsername("john")).thenReturn(Optional.of(user));

        AuthService service = new AuthService(repo, "test-secret");
        String token = service.login("john", "pass");
        assertNotNull(token);
        assertTrue(token.split("\\.").length >= 2); // basic JWT structure
        assertTrue(service.validate(token));
    }
}
