package com.app.userm.dao;

import com.app.userm.entity.*;
import com.app.userm.model.SiteRequModel;

import java.util.List;
import java.util.Optional;

public interface MasterDao {

    RoleMaster addNewRole(RoleMaster roleMaster);
    boolean addMultipleRole(List<RoleMaster> roleList);

    List<RoleMaster> getAllRole();
    Optional<RoleMaster> getRoleByRole(Integer roleId);


    MenuMaster addMenu(MenuMaster menu);
    boolean addMultiMenu(List<MenuMaster> menuList);

    List<MenuMaster> getMenuByRole(Integer role);

    List<MenuMaster> getAllMenu();

    //getting state and district details

    List<StateMaster> getAllState();

    List<DistrictMaster> getDistrictByStateId(Integer stateId);

    Optional<StateMaster> getStateById(Integer id);
    Optional<DistrictMaster> getDisterictById(Integer id);

//get social media details

    List<SocialInfo> getAllSocialMedia();

    List<EventsMaster> getAllEvent();


    List<SiteMaster> getAllSite();

    SiteMaster addSite(SiteMaster request);

}
