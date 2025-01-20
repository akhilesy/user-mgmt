package com.app.userm.dao.impl;

import com.app.userm.constant.ApplicationCnstant;
import com.app.userm.dao.UserDao;
import com.app.userm.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private  UserMasterRepo userRepo;

    @Autowired
    private AuthConfig config;




    @Override
    public UserMaster createUser(UserMaster userMaster) {
        modifyUserdetaild(userMaster);
    return userRepo.save(userMaster);

    }

    /**
     * @return
     */
    @Override
    public List<UserMaster> getAllUserList() {
       return (List<UserMaster>) userRepo.findAll();

    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<UserMaster> getUserById(Integer id) {
        return  userRepo.findById(id);
    }

    /**
     * @param emalId
     * @return
     */
    @Override
    public UserMaster getUserByEmailId(String emalId) {
        return userRepo.findByEmailId(emalId);
    }

    /**
     * @param mobile
     * @return
     */
    @Override
    public UserMaster getUserByMobile(String mobile) {
        return userRepo.findByMobile(mobile);
    }

    /**
     * @param userMaster
     * @return
     */
    @Override
    public UserMaster updateUser(UserMaster userMaster) {
        return userRepo.save(userMaster);
    }

    /**
     * @param userName
     * @param password
     * @return
     */
    @Override
    public  Boolean loginUser(String userName, String password) throws Exception {
        UserMaster userDetails=null;
        if(userName!=null) {
           //  userDetails = userRepo.findByEmailId(userName);
        }


       if(userDetails!=null){
           String decPassword=AuthConfig.decrypt(userDetails.getPassword(),AuthConfig.generateSecretKey());
           if(password.equals(decPassword)){
               return true;
           }else {
                throw new CustomException("password are incorrect");
           }
       }else {
           throw new CustomException("User name or password are incorrect");
       }


    }
    /**
     * @param state
     * @return
     */
    @Override
    public List<Optional<UserMaster>> findByState(Integer state) {
        List<UserMaster> users = userRepo.findByState(state);
        return users.stream()
                .map(Optional::of) // Wrap each UserMaster in an Optional
                .collect(Collectors.toList()); // Collect into a List
    }

    /**
     * @param district
     * @return
     */
    @Override
    public List<Optional<UserMaster>> findByDistrict(Integer district) {
        List<UserMaster> users = userRepo.findByDistrict(district);
        return users.stream()
                .map(Optional::of) // Wrap each UserMaster in an Optional
                .collect(Collectors.toList());
    }

    /**
     * @param workingArea
     * @return
     */
    @Override
    public List<Optional<UserMaster>> findByWorkingArea(String workingArea) {
        List<UserMaster> users = userRepo.findByWorkingArea(workingArea);
        return users.stream()
                .map(Optional::of) // Wrap each UserMaster in an Optional
                .collect(Collectors.toList());
    }

    /**
     * @param designation
     * @return
     */
    @Override
    public List<Optional<UserMaster>> findByDesignation(String designation) {
        List<UserMaster> users = userRepo.findByDesignation(designation);
        return users.stream()
                .map(Optional::of) // Wrap each UserMaster in an Optional
                .collect(Collectors.toList());
    }


    /**
     * @return
     */
    @Override
    public Long getTotalNumberOfUser() {
        return userRepo.count();
    }

    private void modifyUserdetaild(UserMaster userMaster){
        userMaster.setActivationDate(LocalDate.now());
        userMaster.setCreatedBy(ApplicationCnstant.BASE_USER);
        userMaster.setCreatedDate(LocalDate.now());
        userMaster.setIsActive(Boolean.TRUE);
        userMaster.setIsDeleted(Boolean.FALSE);
        userMaster.setLoginType(1);
        userMaster.setExpirationDate(LocalDate.now().plusMonths(6));
    }
}
