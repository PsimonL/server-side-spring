package com.example.serversidespring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerSideSpringApplication {

    public static void main(String[] args) {
        System.out.println("Starting Spring Chat Parring Server...");
        SpringApplication.run(ServerSideSpringApplication.class, args);
    }

}
