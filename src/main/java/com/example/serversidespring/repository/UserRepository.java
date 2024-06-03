package com.example.serversidespring.repository;

import com.example.serversidespring.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByFirstNameAndLastName(String firstName, String lastName);
}
