package com.app.userm.controller;

import com.app.userm.constant.ApplicationCnstant;
import com.app.userm.exception.CustomException;
import com.app.userm.model.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllUsers() {
        ResponceModel<List<UserMaster>> responce;

        List<UserMaster> users = userService.getAllUserList();
        if(!users.isEmpty()){
            responce=new ResponceModel<>(HttpStatus.OK, ApplicationCnstant.DATA_FOUND,users);
            return ResponseEntity.ok().body(responce);
        }else{
            responce=new ResponceModel<>(HttpStatus.OK, ApplicationCnstant.DATA_NOT_FOUND,users);
            return ResponseEntity.ok().body(responce);

        }

    }

    @PostMapping(value = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> saveUserDetails(@Valid @RequestBody UserRequestObject userRequestObject) {
     ResponceModel<UserMaster> responce;
        UserMaster data = userService.saveUser(userRequestObject);
        if(data!=null){
            responce=new ResponceModel<>(HttpStatus.OK, ApplicationCnstant.USER_CREATED,data);
            return ResponseEntity.ok().body(responce);
        }else {
            responce=new ResponceModel<>(HttpStatus.BAD_REQUEST, ApplicationCnstant.USER_NOT_CREATED,data);
            return ResponseEntity.ok().body(responce);
        }

    }

    //update role

    @PostMapping(path = "/updateRole")
    public ResponseEntity<Object> updateRoleOfUser(@RequestParam Integer userId, @RequestParam Integer roleId){
        ResponceModel<UserMaster> responce;
        if (userId == null || roleId == null) {
            responce=new ResponceModel<>(HttpStatus.BAD_REQUEST,ApplicationCnstant.USER_ID_OR_ROLE_NOT_CORRECT);
            return ResponseEntity.badRequest().body(responce);
        }

        // Fetch the user from the repository
        UserMaster user = userService.getUserObjById(userId).orElse(null);

        if (user == null) {
            responce=new ResponceModel<>(HttpStatus.OK,ApplicationCnstant.USER_NOT_FOUND);
            return ResponseEntity.ok().body(responce);
        }
        user=  userService.updateUserRole(userId,roleId,user);
        responce=new ResponceModel<>(HttpStatus.OK,ApplicationCnstant.ROLE_UPDATED,user);

        return ResponseEntity.badRequest().body(user);
    }

    @GetMapping("/getUser")
    ResponseEntity<Object> getUserById(@RequestParam("userId") Integer userId) {
       ResponceModel<CustomUserResponce> responce;
        if (userId == null) {
            responce=new ResponceModel<>(HttpStatus.BAD_REQUEST,ApplicationCnstant.USER_NOT_PROVIDED);
            return ResponseEntity.badRequest().body(responce);
        }

        // Fetch the user from the repository
        CustomUserResponce user = userService.getUserById(userId).orElse(null);

        if (user == null) {
            responce=new ResponceModel<>(HttpStatus.OK,ApplicationCnstant.USER_NOT_FOUND);
            return ResponseEntity.ok().body(responce);
        }
        responce=new ResponceModel<>(HttpStatus.OK,ApplicationCnstant.USER_FOUND,user);
        return ResponseEntity.ok().body(responce);


    }

    @PostMapping(path = "/login")
    ResponseEntity<Object> loginUser(@RequestBody  LoginRequest login){
        ResponceModel<LoginResponceModel> responce;
        try{
            if(login.getUserName()!=null && login.getPassword()!=null){
               LoginResponceModel resonceMod= userService.loginUser(login);
                responce=new ResponceModel<>(HttpStatus.OK,ApplicationCnstant.USER_LOGIN,resonceMod);

               return ResponseEntity.ok().body(responce);
            }else{
                responce=new ResponceModel<>(HttpStatus.BAD_REQUEST,ApplicationCnstant.USER_UNAUTHORISED);

                return ResponseEntity.badRequest().body(responce);
            }
        }catch (CustomException ex){
            responce=new ResponceModel<>(HttpStatus.BAD_REQUEST,ex.getMessage());
            return ResponseEntity.internalServerError().body(responce);

        }catch (Exception e){
            e.printStackTrace();
            responce=new ResponceModel<>(HttpStatus.BAD_REQUEST,ApplicationCnstant.INTERNAL_SERVER_ERROR);
            return ResponseEntity.internalServerError().body(responce);
        }



    }
    @GetMapping("/getUserByMobile")
    public ResponseEntity<Object> getUserByMobile(@RequestParam("mobile") String mobile) {
        ResponceModel<CustomUserResponce> responce;

        // Check if mobile is null or empty
        if (mobile == null || mobile.trim().isEmpty()) {
            responce = new ResponceModel<>(HttpStatus.BAD_REQUEST, ApplicationCnstant.USER_NOT_PROVIDED);
            return ResponseEntity.badRequest().body(responce);
        }

        // Fetch the user from the repository
        CustomUserResponce user = userService.getUserByMobile(mobile);

        if (user == null) {
            responce = new ResponceModel<>(HttpStatus.OK, ApplicationCnstant.USER_NOT_FOUND);
            return ResponseEntity.ok().body(responce);
        }

        responce = new ResponceModel<>(HttpStatus.OK, ApplicationCnstant.USER_FOUND, user);
        return ResponseEntity.ok().body(responce);
    }

    @GetMapping("/getUserByState")
    public ResponseEntity<Object> getUserByState(@RequestParam("state") Integer state) {
        ResponceModel<List<CustomUserResponce>> responce;

        // Check if state is null
        if (state == null) {
            responce = new ResponceModel<>(HttpStatus.BAD_REQUEST, ApplicationCnstant.USER_NOT_PROVIDED);
            return ResponseEntity.badRequest().body(responce);
        }

        // Fetch users based on state
        List<CustomUserResponce> users = userService.getUserByState(state);

        if (users == null || users.isEmpty()) {
            responce = new ResponceModel<>(HttpStatus.OK, ApplicationCnstant.USER_NOT_FOUND);
            return ResponseEntity.ok().body(responce);
        }

        responce = new ResponceModel<>(HttpStatus.OK, ApplicationCnstant.USER_FOUND, users);
        return ResponseEntity.ok().body(responce);
    }

    @GetMapping("/getUserByDistrict")
    public ResponseEntity<Object> getUserByDistrict(@RequestParam("district") Integer district) {
        ResponceModel<List<CustomUserResponce>> responce;

        // Check if district is null
        if (district == null) {
            responce = new ResponceModel<>(HttpStatus.BAD_REQUEST, ApplicationCnstant.USER_NOT_PROVIDED);
            return ResponseEntity.badRequest().body(responce);
        }

        // Fetch users based on district
        List<CustomUserResponce> users = userService.getUserByDistrict(district);

        if (users == null || users.isEmpty()) {
            responce = new ResponceModel<>(HttpStatus.OK, ApplicationCnstant.USER_NOT_FOUND);
            return ResponseEntity.ok().body(responce);
        }

        responce = new ResponceModel<>(HttpStatus.OK, ApplicationCnstant.USER_FOUND, users);
        return ResponseEntity.ok().body(responce);
    }

    @GetMapping("/getUserByDesignation")
    public ResponseEntity<Object> getUserByDesignation(@RequestParam("designation") String designation) {
        ResponceModel<List<CustomUserResponce>> responce;

        // Check if designation is null or empty
        if (designation == null || designation.trim().isEmpty()) {
            responce = new ResponceModel<>(HttpStatus.BAD_REQUEST, ApplicationCnstant.USER_NOT_PROVIDED);
            return ResponseEntity.badRequest().body(responce);
        }

        // Fetch users based on designation
        List<CustomUserResponce> users = userService.getUserByDesignation(designation);

        if (users == null || users.isEmpty()) {
            responce = new ResponceModel<>(HttpStatus.OK, ApplicationCnstant.USER_NOT_FOUND);
            return ResponseEntity.ok().body(responce);
        }

        responce = new ResponceModel<>(HttpStatus.OK, ApplicationCnstant.USER_FOUND, users);
        return ResponseEntity.ok().body(responce);
    }

    @GetMapping("/getUserByWorkingArea")
    public ResponseEntity<Object> getUserByWorkingArea(@RequestParam("workingArea") String workingArea) {
        ResponceModel<List<CustomUserResponce>> responce;

        // Check if workingArea is null or empty
        if (workingArea == null || workingArea.trim().isEmpty()) {
            responce = new ResponceModel<>(HttpStatus.BAD_REQUEST, ApplicationCnstant.USER_NOT_PROVIDED);
            return ResponseEntity.badRequest().body(responce);
        }

        // Fetch users based on working area
        List<CustomUserResponce> users = userService.getUserByWorkingArea(workingArea);

        if (users == null || users.isEmpty()) {
            responce = new ResponceModel<>(HttpStatus.OK, ApplicationCnstant.USER_NOT_FOUND);
            return ResponseEntity.ok().body(responce);
        }

        responce = new ResponceModel<>(HttpStatus.OK, ApplicationCnstant.USER_FOUND, users);
        return ResponseEntity.ok().body(responce);
    }


    @GetMapping("/getUserCount")
    ResponseEntity<Object> getUserCount() {
        ResponceModel<Long> responce;

        Long userCount = userService.getTotalNumberOfUser();
        if (userCount == null) {
            responce = new ResponceModel<>(HttpStatus.OK, ApplicationCnstant.DATA_NOT_FOUND);
            return ResponseEntity.ok().body(responce);
        }
        responce = new ResponceModel<>(HttpStatus.OK, ApplicationCnstant.DATA_FOUND, userCount);
        return ResponseEntity.ok().body(responce);

    }


    }


