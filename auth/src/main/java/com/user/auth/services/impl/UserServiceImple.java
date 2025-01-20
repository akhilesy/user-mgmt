package com.user.auth.services.impl;

import com.user.auth.constant.ApplicationConstants;
import com.user.auth.constant.EnumConstant;
import com.user.auth.dto.UserRegisterRequest;
import com.user.auth.dto.UserRespObject;
import com.user.auth.entity.Users;
import com.user.auth.exception.CustomException;
import com.user.auth.exception.EntityNotFoundException;
import com.user.auth.repo.UserRepository;
import com.user.auth.services.MasterService;
import com.user.auth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImple implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MasterService masterService;

    @Override
    public Users register(UserRegisterRequest register) throws CustomException {
        Users users=new Users();
        try{
            createUserObject(register,users);
            users.setPassword(passwordEncoder.encode(register.getPassword()));
            return userRepository.save(users);
        }catch (Exception ex){
            throw new CustomException("User not created ");
        }
    }
    @Override
    public List<UserRespObject> getAllUser() {
       List<Users> usrList= userRepository.findAll();
       List<UserRespObject> responceObj=new ArrayList<UserRespObject>();

        responceObj.addAll(usrList.stream().map(user->{
            UserRespObject resp=new UserRespObject();
            createRespUser(resp,user);
            return resp;
        }).toList());


        return  responceObj!=null?responceObj: Collections.emptyList();
    }

    @Override
    public UserRespObject getUserById(String userId) {
        Users user=userRepository.findByEmailId(userId);
        UserRespObject respObject = new UserRespObject();
        if (user != null) {
            createRespUser(  respObject, user);
        } else {
            throw new EntityNotFoundException("User not found with email: " + userId);
        }

        return respObject;
    }

   public void createRespUser(UserRespObject respObject,Users user){
       respObject.setFullName(user.getFirstName() + " " + user.getLastName());
       respObject.setFatherName(user.getFatherName());
       respObject.setMobile(user.getMobile());
       respObject.setEmailId(user.getEmailId());
       respObject.setAddress(user.getAddress());
       respObject.setCountry(ApplicationConstants.INDIA);
       respObject.setDistrict(user.getDistrict().toString());
       respObject.setState(user.getState().toString());
       respObject.setRole(EnumConstant.Role.valueOf(user.getRole()).toUpperCase());
       respObject.setIsActive(user.getIsActive());
       respObject.setDeleted(user.getIsDeleted());
       respObject.setExpirationDate(user.getExpirationDate());
    }

    private void createUserObject(UserRegisterRequest register, Users users) {

        users.setId(masterService.generateSequence(Users.SEQUENCE_USERS));
        users.setFirstName(register.getFirstName());
        users.setLastName(register.getLastName());
        users.setFatherName(register.getFatherName());
        users.setAddress(register.getAddress());
        users.setDistrict(register.getDistrict());
        users.setState(register.getState());
        users.setCountry(register.getCountry());
        users.setEmailId(register.getEmailId());
        users.setMobile(register.getMobile());
        users.setRole(register.getRole());
        users.setCreatedBy("admin");
        users.setActivationDate(LocalDateTime.now());
        users.setCreatedDate(LocalDateTime.now());
        users.setLoginType(1);
        users.setExpirationDate(LocalDateTime.now().plusYears(2));

    }


}
