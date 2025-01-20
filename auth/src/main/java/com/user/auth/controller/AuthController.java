package com.user.auth.controller;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.user.auth.constant.ApplicationConstants;
import com.user.auth.dto.LoginDto;
import com.user.auth.dto.ResponceModel;
import com.user.auth.dto.UserRegisterRequest;
import com.user.auth.entity.Users;
import com.user.auth.exception.CustomException;
import com.user.auth.exception.EntityNotFoundException;
import com.user.auth.services.AuthService;
import com.user.auth.services.UserService;
import com.user.auth.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDto login) {
        ResponceModel<Object> responseModel;

        try {
            String authenticatedUser = authService.authenticate(login.getUserId(), login.getPassword());

            if (!authenticatedUser.isEmpty()) {
                responseModel = new ResponceModel<>(HttpStatus.OK, ApplicationConstants.DATA_FOUND, authenticatedUser);
                return ResponseEntity.ok(responseModel);
            } else {
                responseModel = new ResponceModel<>(HttpStatus.BAD_REQUEST, ApplicationConstants.DATA_NOT_FOUND);
                return ResponseEntity.badRequest().body(responseModel);
            }
        } catch (EntityNotFoundException ex) {
            responseModel = new ResponceModel<>(HttpStatus.BAD_REQUEST, ex.getMessage());
            return ResponseEntity.badRequest().body(responseModel);
        } catch (Exception ex) {
            responseModel = new ResponceModel<>(HttpStatus.INTERNAL_SERVER_ERROR, ApplicationConstants.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseModel);
        }
    }

@GetMapping("/validateToken")
    public ResponseEntity<Object> validateToken(@RequestParam String token){
        ResponceModel<Object> responseModel=null;
        if(!token.isEmpty()) {
            boolean isToken = authService.validateToken(token);
            if(isToken){
                responseModel = new ResponceModel<>(HttpStatus.OK, ApplicationConstants.TOKEN_VALIDATED);
                return ResponseEntity.ok(responseModel);
            }else {
                responseModel = new ResponceModel<>(HttpStatus.BAD_REQUEST, ApplicationConstants.TOKEN_VALIDATED_FAIL);
                return ResponseEntity.badRequest().body(responseModel);
            }
        }
        responseModel = new ResponceModel<>(HttpStatus.BAD_REQUEST, ApplicationConstants.INTERNAL_SERVER_ERROR);
        return ResponseEntity.internalServerError().body(responseModel);
    }
}
