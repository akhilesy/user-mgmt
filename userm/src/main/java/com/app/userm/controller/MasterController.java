package com.app.userm.controller;

import com.app.userm.constant.ApplicationCnstant;
import com.app.userm.entity.*;
import com.app.userm.model.MenuRequestObject;
import com.app.userm.model.ResponceModel;
import com.app.userm.model.SiteRequModel;
import com.app.userm.service.MasterServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/master")
public class MasterController {

    @Autowired
    private MasterServices masterServices;

    @PostMapping(path = "/add-menu", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> addNewMenu(@RequestBody MenuRequestObject menuObj){

        if(menuObj!=null){

          MenuMaster master= masterServices.addMenu(menuObj);
            return ResponseEntity.ok().body("menu added successfully menu id"+master.getMenuId());
        }else {
            return ResponseEntity.badRequest().body("menu not added");
        }


    }

    @GetMapping(path = "/getMenu",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> getmenuList() {
        ResponceModel<List<MenuMaster>> responce;
        List<MenuMaster> lisMaster = masterServices.getAllmenu();
        if (!lisMaster.isEmpty()) {
            responce=new ResponceModel<>(HttpStatus.OK, ApplicationCnstant.DATA_FOUND,lisMaster);
            return ResponseEntity.ok().body(responce);
        } else {
            responce=new ResponceModel<>(HttpStatus.NOT_FOUND,ApplicationCnstant.DATA_NOT_FOUND,lisMaster);
            return ResponseEntity.ok().body(responce);

        }
    }



    @GetMapping(path = "/getDistrict",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> getDistrict(@RequestParam Integer stateId){
        ResponceModel<List<DistrictMaster>>  responce;
        List<DistrictMaster> lisMaster=masterServices.getDistrictByStateId(stateId);
        if (!lisMaster.isEmpty()) {
            responce=new ResponceModel<>(HttpStatus.OK, ApplicationCnstant.DATA_FOUND,lisMaster);
            return ResponseEntity.ok().body(responce);
        } else {
            responce=new ResponceModel<>(HttpStatus.NOT_FOUND,ApplicationCnstant.DATA_NOT_FOUND,lisMaster);
            return ResponseEntity.ok().body(responce);

        }

    }

    @GetMapping(path = "/allState",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> getStateList(){
        ResponceModel<List<StateMaster>>  responce;

        List<StateMaster> lisMaster=masterServices.getAllState();
        System.out.println(lisMaster);

        if(!lisMaster.isEmpty()){
            responce=new ResponceModel<>(HttpStatus.OK, ApplicationCnstant.DATA_FOUND,lisMaster);
            return ResponseEntity.ok().body(responce);
        }else {
            responce=new ResponceModel<>(HttpStatus.NOT_FOUND,ApplicationCnstant.DATA_NOT_FOUND,lisMaster);
            return ResponseEntity.ok().body(responce);
        }

    }

    @GetMapping(path = "media")
ResponseEntity<Object> getAllSocialMedia(){
        ResponceModel <List<SocialInfo>> responce;
        List<SocialInfo> listInfo=masterServices.getAllSocialMedia();
        if(listInfo.isEmpty()){
            responce=new ResponceModel<>(HttpStatus.NOT_FOUND,ApplicationCnstant.DATA_NOT_FOUND);
            return ResponseEntity.ok(responce);
        }else{
            responce=new ResponceModel<>(HttpStatus.OK,ApplicationCnstant.DATA_FOUND,listInfo);
            return ResponseEntity.ok(responce);

        }
}


    @GetMapping(path = "events")
    ResponseEntity<Object> getAllEvents(){
        ResponceModel <List<EventsMaster>> responce;
        List<EventsMaster> listInfo=masterServices.getAllEvents();
        if(listInfo.isEmpty()){
            responce=new ResponceModel<>(HttpStatus.NOT_FOUND,ApplicationCnstant.DATA_NOT_FOUND);
            return ResponseEntity.ok(responce);
        }else{
            responce=new ResponceModel<>(HttpStatus.OK,ApplicationCnstant.DATA_FOUND,listInfo);
            return ResponseEntity.ok(responce);

        }
    }

    @PostMapping(path = "/add-site", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> addSte(@RequestBody SiteRequModel menuObj){
        ResponceModel <SiteMaster> responce;
        if(menuObj!=null){

            SiteMaster master= masterServices.addSite(menuObj);
            if(master!=null){
                responce=new ResponceModel<>(HttpStatus.CREATED
                ,ApplicationCnstant.SITE_ADDED,master);

                return ResponseEntity.ok().body(responce);
            }else{
                responce=new ResponceModel<>(HttpStatus.INTERNAL_SERVER_ERROR
                        ,ApplicationCnstant.SITE_NOT_ADDED,master);

                return ResponseEntity.internalServerError().body(responce);
            }

        }else {
            return ResponseEntity.badRequest().body("Site not added");
        }


    }

    @GetMapping(path = "allSite")
    ResponseEntity<Object> getAllSites(){
        ResponceModel <List<SiteMaster>> responce;
        List<SiteMaster> listInfo=masterServices.getAllSite();
        if(listInfo.isEmpty()){
            responce=new ResponceModel<>(HttpStatus.NOT_FOUND,ApplicationCnstant.DATA_NOT_FOUND);
            return ResponseEntity.ok(responce);
        }else{
            responce=new ResponceModel<>(HttpStatus.OK,ApplicationCnstant.DATA_FOUND,listInfo);
            return ResponseEntity.ok(responce);

        }
    }


}
