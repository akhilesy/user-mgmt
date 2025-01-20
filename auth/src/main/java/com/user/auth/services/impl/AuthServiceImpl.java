package com.user.auth.services.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.user.auth.dto.UserRegisterRequest;
import com.user.auth.entity.Users;
import com.user.auth.exception.CustomException;
import com.user.auth.exception.EntityNotFoundException;
import com.user.auth.repo.UserRepository;
import com.user.auth.services.AuthService;
import com.user.auth.services.MasterService;
import com.user.auth.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MasterService masterService;
    @Autowired
    private JwtUtil jwtUtil;


    @Override
    public String authenticate(String username, String password) {
        Users user = userRepository.findByEmailId(username);

        if (user == null || user.getPassword() == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new EntityNotFoundException("Invalid username or password");
        }

        try {
            return jwtUtil.generateToken(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error generating token", e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error during authentication", e);
        }
    }

    @Override
    public boolean validateToken(String token) {
        return  jwtUtil.validateToken(token);

    }

}
