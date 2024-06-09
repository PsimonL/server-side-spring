package com.example.serversidespring.service;

import com.example.serversidespring.model.AppUser;
import com.example.serversidespring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public String register(AppUser user) {
        AppUser existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            System.out.println("User already exists with email: " + user.getEmail());
            return "User already exists";
        }
        userRepository.save(user);
        System.out.println("User registered successfully: " + user.getEmail());
        return "User registered successfully";
    }

    public String login(AppUser user) {
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
