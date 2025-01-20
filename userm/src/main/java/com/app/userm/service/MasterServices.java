package com.app.userm.service;

import com.app.userm.entity.*;
import com.app.userm.model.MenuRequestObject;
import com.app.userm.model.SiteRequModel;

import java.util.List;

public interface MasterServices {


    MenuMaster addMenu(MenuRequestObject menu);
    boolean addMultiMenu(List<MenuMaster> menuList);

    List<MenuMaster> getMenuByRoleId(Integer roleId);

    List<MenuMaster> getAllmenu();


    List<StateMaster> getAllState();

    List<DistrictMaster> getDistrictByStateId(Integer stateId);
//add social media

    List<SocialInfo> getAllSocialMedia();

    List<EventsMaster> getAllEvents();

    List<SiteMaster> getAllSite();

    SiteMaster addSite(SiteRequModel request);


}
