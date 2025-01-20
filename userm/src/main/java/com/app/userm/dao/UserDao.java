package com.app.userm.dao;

import java.util.List;
import java.util.Optional;


public interface UserDao {

     UserMaster createUser(UserMaster userMaster);

     List<UserMaster> getAllUserList();

     Optional<UserMaster> getUserById(Integer id);

     UserMaster getUserByEmailId(String emalId);

     UserMaster getUserByMobile(String mobile);

     UserMaster updateUser(UserMaster userMaster);



     public  Boolean loginUser(String userName, String password) throws Exception;

     List<Optional<UserMaster>> findByState(Integer state);

     List<Optional<UserMaster>> findByDistrict(Integer district);
     List<Optional<UserMaster>> findByWorkingArea(String workingArea);
     List<Optional<UserMaster>> findByDesignation(String designation);

     Long getTotalNumberOfUser();
}
