package com.user.auth.services;

import com.user.auth.dto.UserRegisterRequest;
import com.user.auth.dto.UserRespObject;
import com.user.auth.entity.Users;
import com.user.auth.exception.CustomException;

import java.util.List;

public interface UserService {

    public Users register(UserRegisterRequest register) throws CustomException;

    public List<UserRespObject> getAllUser() ;

    public UserRespObject getUserById(String userId) ;

}
