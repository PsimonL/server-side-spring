package com.example.serversidespring.service;

import com.example.serversidespring.entity.AppUser;
import com.example.serversidespring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUser register(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return userRepository.save(appUser);
    }

    public boolean login(String username, String password) {
        AppUser appUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("AppUser not found"));

        return passwordEncoder.matches(password, appUser.getPassword());
    }
}
