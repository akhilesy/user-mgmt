package com.user.auth.controller;

import com.user.auth.constant.ApplicationConstants;
import com.user.auth.dto.ResponceModel;
import com.user.auth.dto.UserRegisterRequest;
import com.user.auth.dto.UserRespObject;
import com.user.auth.entity.Users;
import com.user.auth.exception.CustomException;
import com.user.auth.services.AuthService;
import com.user.auth.services.UserService;
import com.user.auth.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UserRegisterRequest register) {

        ResponceModel<Object> responce=null;
        try {
            Users users = userService.register(register);
            if (users != null) {
                responce = new ResponceModel<>(HttpStatus.CREATED, ApplicationConstants.USER_CREATED, register);
                return ResponseEntity.status(HttpStatus.CREATED).body(responce);
            } else {
                responce = new ResponceModel<>(HttpStatus.BAD_REQUEST, ApplicationConstants.USER_NOT_CREATED);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responce);
            }
        }catch (CustomException cus){
            responce = new ResponceModel<>(HttpStatus.INTERNAL_SERVER_ERROR, cus.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responce);
        }catch (Exception ex){
            responce = new ResponceModel<>(HttpStatus.INTERNAL_SERVER_ERROR, ApplicationConstants.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responce);
        }
    }

    @GetMapping("/alluser")
    public  ResponseEntity<Object>   getAllUser(){
        ResponceModel<Object> responce=null;
        List<UserRespObject> users =userService.getAllUser();
        if (users != null) {
            responce = new ResponceModel<>(HttpStatus.OK, ApplicationConstants.DATA_FOUND, users);
            return ResponseEntity.status(HttpStatus.OK).body(responce);
        } else {
            responce = new ResponceModel<>(HttpStatus.BAD_REQUEST, ApplicationConstants.DATA_NOT_FOUND);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responce);
        }

    }

    @GetMapping("/getUser")
    public  ResponseEntity<Object>   getUserById(@RequestParam String emailId){
        ResponceModel<Object> responce=null;
        UserRespObject users =userService.getUserById(emailId);
        if (users != null) {
            responce = new ResponceModel<>(HttpStatus.OK, ApplicationConstants.DATA_FOUND, users);
            return ResponseEntity.status(HttpStatus.OK).body(responce);
        } else {
            responce = new ResponceModel<>(HttpStatus.BAD_REQUEST, ApplicationConstants.DATA_NOT_FOUND);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responce);
        }

    }

}
