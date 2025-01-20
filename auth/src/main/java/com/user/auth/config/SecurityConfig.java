package com.user.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/auth/**","/master/**","/user/**").permitAll() // Allow public access to specific endpoints
                        .anyRequest().authenticated()       // All other endpoints require authentication
                )

                .csrf(csrf -> csrf
                        .disable()               // Disable CSRF for simplicity (not recommended in production)
                );

        return http.build();
    }
}
