package com.example.serversidespring.repository;

import com.example.serversidespring.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByEmailAndPassword(String email, String password);
    AppUser findByEmail(String email);
}
