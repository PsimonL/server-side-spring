package com.example.serversidespring.controller;

import com.example.serversidespring.model.AppUser;
import com.example.serversidespring.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port;
        userRepository.deleteAll();
    }

    @Test
    void testRegisterAndLogin() {
        AppUser user = new AppUser();
        user.setEmail("test@example.com");
        user.setPassword("password");

        // Test registration
        ResponseEntity<String> registerResponse = restTemplate.postForEntity(baseUrl + "/auth/register", user, String.class);
        assertThat(registerResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(registerResponse.getBody()).isEqualTo("User registered successfully");

        // Test login
        ResponseEntity<String> loginResponse = restTemplate.postForEntity(baseUrl + "/auth/login", user, String.class);
        assertThat(loginResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(loginResponse.getBody()).isEqualTo("User logged in successfully");
    }
}
