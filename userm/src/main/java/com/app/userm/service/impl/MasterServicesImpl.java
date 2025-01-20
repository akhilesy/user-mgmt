package com.app.userm.service.impl;

import com.app.userm.dao.MasterDao;
import com.app.userm.entity.*;
import com.app.userm.model.MenuRequestObject;
import com.app.userm.model.SiteRequModel;
import com.app.userm.service.MasterServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class MasterServicesImpl implements MasterServices {

    @Autowired
    private MasterDao masterDao;

    /**
     * @param menuObj
     * @return
     */
    @Override
    public MenuMaster addMenu(MenuRequestObject menuObj) {
        MenuMaster menuMaster=new MenuMaster();
        menuMaster.setMenuName(menuObj.getMenuName());
        menuMaster.setMenuDes(menuObj.getMenuDes());
        menuMaster.setMenuOrder(menuObj.getMenuOrder());
        menuMaster.setMenuUrl(menuObj.getMenuUrl());
        menuMaster.setSubMenu(menuObj.getSubMenu());
        menuMaster.setParentMenuId(0);
       // RoleMaster roleMater= (RoleMaster) masterDao.getRoleByRole(menuObj.getRoleId()).get();
        //menuMaster.setRole(roleMater);
        return masterDao.addMenu(menuMaster);
    }

    /**
     * @param menuList
     * @return
     */
    @Override
    public boolean addMultiMenu(List<MenuMaster> menuList) {
        return false;
    }

    /**
     * @param role
     * @return
     */
    @Override
    public List<MenuMaster> getMenuByRoleId(Integer roleId) {
              return masterDao.getMenuByRole(roleId);
    }

    /**
     * @return
     */
    @Override
    public List<MenuMaster> getAllmenu() {
        return masterDao.getAllMenu();
    }


    /**
     * @return
     */
    @Override
    public List<StateMaster> getAllState() {
        return masterDao.getAllState();
    }

    /**
     * @param stateId
     * @return
     */
    @Override
    public List<DistrictMaster> getDistrictByStateId(Integer stateId) {
        return masterDao.getDistrictByStateId(stateId);
    }

    /**
     * @return
     */
    @Override
    public List<SocialInfo> getAllSocialMedia() {
        return masterDao.getAllSocialMedia().stream().
                sorted(Comparator.comparing(SocialInfo::getId).reversed()).toList();
    }

    /**
     * @return
     */
    @Override
    public List<EventsMaster> getAllEvents() {
        return masterDao.getAllEvent().stream()
                .sorted(Comparator.comparing(EventsMaster::getId).reversed()) // Sort by the id field
                .toList();
    }

    /**
     * @return
     */
    @Override
    public List<SiteMaster> getAllSite() {
        return masterDao.getAllSite();
    }

    /**
     * @return
     */
    @Override
    public SiteMaster addSite(SiteRequModel request) {
        SiteMaster sm=new SiteMaster();
        sm.setIsActive(Boolean.TRUE);
        sm.setCreatedDate(LocalDateTime.now());
        sm.setCreatedBy("admin");
        sm.setIsDeleted(Boolean.FALSE);
        sm.setOwnerMobile(request.getOwnerMobile());
        sm.setOwnerName(request.getOwnerName());
        sm.setSiteName(request.getSiteName());
        sm.setSiteDescription(request.getWorkDescription());
        return masterDao.addSite(sm);
    }
}
