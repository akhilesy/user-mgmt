package com.app.userm.dao.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.app.userm.entity.*;
import com.app.userm.model.SiteRequModel;
import com.app.userm.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.userm.constant.ApplicationCnstant;
import com.app.userm.dao.MasterDao;

@Repository
public class MasterDaoImpl implements MasterDao {


    @Autowired
    private MenuMasterRepo menuRepo;

    @Autowired
    private StateMasterRepo stateMaster;
    @Autowired
    private DistrictMasterRepo districtMaster;

    @Autowired
    private SocialRepo socialRepo;

    @Autowired
    private EventMasterRepo eventRepo;

    @Autowired
    private SiteRepo siteRepo;

    /**
     * @param menu
     * @return
     */
    @Override
    public MenuMaster addMenu(MenuMaster menu) {

     addMetadataOfMenu(menu);
        return menuRepo.save(menu);
    }

   MenuMaster addMetadataOfMenu(MenuMaster menu){
        menu.setCreatedBy("admin");
        menu.setCreatedDate(LocalDate.now());
        menu.setIsActive(Boolean.TRUE);
        menu.setIsDeleted(Boolean.FALSE);
        return menu;
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
    public List<MenuMaster> getMenuByRole(Integer role) {
        return menuRepo.findByRoleRoleId(role);
    }

    /**
     * @return
     */
    @Override
    public List<MenuMaster> getAllMenu() {
        return (List<MenuMaster>) menuRepo.findAll();
    }

    /**
     * @return
     */
    @Override
    public List<StateMaster> getAllState() {
        return (List<StateMaster>) stateMaster.findAll();
    }

    /**
     * @param stateId
     * @return
     */
    @Override
    public List<DistrictMaster> getDistrictByStateId(Integer stateId) {
        if(stateId!=null){
            return districtMaster.findByState_StateId(stateId);
        }
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<StateMaster> getStateById(Integer id) {
        return stateMaster.findById(id);
    }

    /**
     * @param Id
     * @return
     */
    @Override
    public Optional<DistrictMaster> getDisterictById(Integer id) {
        return districtMaster.findById(id);
    }

    /**
     * @return
     */
    @Override
    public List<SocialInfo> getAllSocialMedia() {
        return (List<SocialInfo>) socialRepo.findAll();
    }

    /**
     * @return
     */
    @Override
    public List<EventsMaster> getAllEvent() {
        return (List<EventsMaster>) eventRepo.findAll();
    }

    /**
     * @return
     */
    @Override
    public List<SiteMaster> getAllSite() {
        return (List<SiteMaster>) siteRepo.findAll();
    }

    /**
     * @return
     */
    @Override
    public SiteMaster addSite(SiteMaster request) {

        return siteRepo.save(request);
    }
}
