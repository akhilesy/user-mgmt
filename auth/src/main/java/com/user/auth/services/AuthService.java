package com.user.auth.services;

import com.user.auth.dto.UserRegisterRequest;
import com.user.auth.entity.Users;
import com.user.auth.exception.CustomException;

public interface AuthService {

    public String authenticate(String username, String password);

    public boolean validateToken(String token);

}
