package com.user.auth.controller;

import com.user.auth.constant.ApplicationConstants;
import com.user.auth.dto.RoleDto;
import com.user.auth.entity.RoleMaster;
import com.user.auth.services.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/master")
public class MasterController {

    @Autowired
    MasterService masterService;

    @PostMapping(path = ApplicationConstants.ADD_ROLE,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addNewRole(@RequestBody RoleDto roleRequest){
        if(roleRequest!=null){
            masterService.addRole(roleRequest);
            return new ResponseEntity<>(ApplicationConstants.ROLE_ADDED, HttpStatus.OK);

        }

        return new ResponseEntity<>("Role Not Added ", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = ApplicationConstants.GET_ROLE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllRoles() {
        // Retrieve the list of roles from the service
        List<RoleMaster> roleMasters = masterService.getAllRole();
        // Check if the list is not empty and return OK with the list
        if (roleMasters != null && !roleMasters.isEmpty()) {
            return ResponseEntity.ok(roleMasters);
        }
        // Return NOT_FOUND status with a message if no roles are found
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No roles found.");
    }

}
