package com.example.serversidespring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.serversidespring.model.AppUser;
import com.example.serversidespring.service.AuthService;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/auth/login")
    public ResponseEntity<String> login(@RequestBody AppUser user) {
        String result = authService.login(user);
        if (result.equals("User logged in successfully")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(403).body(result);
        }
    }

    @PostMapping("/auth/register")
    public ResponseEntity<String> register(@RequestBody AppUser user) {
        String result = authService.register(user);
        if (result.equals("User registered successfully")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(403).body(result);
        }
    }
}
