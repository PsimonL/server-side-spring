package com.example.serversidespring.controller;

import com.example.serversidespring.model.AppUser;
import com.example.serversidespring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public String register(@RequestBody AppUser user) {
        AppUser existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            System.out.println("User already exists with email: " + user.getEmail());
            return "User already exists";
        }
        userRepository.save(user);
        System.out.println("User registered successfully: " + user.getEmail());
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody AppUser user) {
        AppUser existingUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if (existingUser != null) {
            System.out.println("User logged in successfully: " + user.getEmail());
            return "User logged in successfully";
        } else {
            System.out.println("Login failed for user: " + user.getEmail());
            return "Login failed";
        }
    }
}
