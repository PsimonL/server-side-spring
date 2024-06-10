//package com.example.serversidespring.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.header.writers.StaticHeadersWriter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
//
//import java.util.Collections;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .cors(cors -> {
//                    CorsConfiguration config = new CorsConfiguration();
//                    config.setAllowCredentials(true);
//                    config.setAllowedOriginPatterns(Collections.singletonList("http://localhost:3000"));
//                    config.setAllowedHeaders(Collections.singletonList("*"));
//                    config.setAllowedMethods(Collections.singletonList("GET, POST, PUT, OPTIONS, DELETE, PATCH"));
//                    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//                    source.registerCorsConfiguration("/**", config);
//                    cors.configurationSource(source);
//                })
//
////                .csrf(csrf -> csrf
////                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
////                )
//
//                .csrf(AbstractHttpConfigurer::disable)
////                .headers(headers -> headers
////                        .addHeaderWriter(new StaticHeadersWriter("Content-Security-Policy",
////                                "default-src 'self'; " +
////                                        "connect-src 'self' http://localhost:3000 ws://localhost:3000 wss://localhost:3000 https://localhost:3000; " +
////                                        "script-src 'self' 'unsafe-inline' http://localhost:3000; " +
////                                        "img-src 'self'; " +
////                                        "style-src 'self' 'unsafe-inline';"))
////                )
//
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/auth/login", "/auth/register", "/csrf-token").permitAll()
//                        .anyRequest().authenticated()
//                );
//
//                // SQL Injection is being prohibited by JPA/Hibernate frameworks.
//                // So using for example:  AppUser existingUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
//                // By default would prevent SQL Injection attack.
//
//        return http.build();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("password")
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }
//}
