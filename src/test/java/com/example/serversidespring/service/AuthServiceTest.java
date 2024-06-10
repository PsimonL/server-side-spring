package com.example.serversidespring.service;

import com.example.serversidespring.model.AppUser;
import com.example.serversidespring.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        AppUser user = new AppUser();
        user.setEmail("test@example.com");
        user.setPassword("password");

        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(null);
        Mockito.when(userRepository.findByEmailAndPassword(anyString(), anyString())).thenReturn(user);
        Mockito.when(userRepository.save(Mockito.any(AppUser.class))).thenReturn(user);
    }

    @Test
    void testRegister() {
        AppUser user = new AppUser();
        user.setEmail("test@example.com");

        String result = authService.register(user);
        assertEquals("User registered successfully", result);
    }

    @Test
    void testLogin() {
        AppUser user = new AppUser();
        user.setEmail("test@example.com");
        user.setPassword("password");

        String result = authService.login(user);
        assertEquals("User logged in successfully", result);
    }
}
