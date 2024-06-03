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
    public AppUser register(@RequestBody AppUser appUser) {
        return userRepository.save(appUser);
    }

    @PostMapping("/login")
    public AppUser login(@RequestBody AppUser appUser) {
        AppUser foundAppUser = userRepository.findByFirstNameAndLastName(appUser.getFirstName(), appUser.getLastName());
        if (foundAppUser != null) {
            return foundAppUser;
        } else {
            throw new RuntimeException("Invalid login credentials");
        }
    }
}
