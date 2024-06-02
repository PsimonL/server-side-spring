package com.example.serversidespring.controller;

import com.example.serversidespring.entity.AppUser;
import com.example.serversidespring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public AppUser register(@RequestBody AppUser appUser) {
        return userService.register(appUser);
    }

    @PostMapping("/login")
    public boolean login(@RequestBody AppUser appUser) {
        return userService.login(appUser.getUsername(), appUser.getPassword());
    }
}
