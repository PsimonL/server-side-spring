package com.example.serversidespring.controller;

import com.example.serversidespring.model.AppUser;
import com.example.serversidespring.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody AppUser user) {
        return authService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody AppUser user) {
        return authService.login(user);
    }
}
