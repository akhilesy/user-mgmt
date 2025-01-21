package com.app.userm.controller;

import com.app.userm.constant.ApplicationCnstant;
import com.app.userm.entity.EmployeeDetails;
import com.app.userm.model.EmployeeRequest;
import com.app.userm.model.ResponceModel;
import com.app.userm.service.AttandanceService;
import com.app.userm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    AttandanceService attandanceService;
    //create employee

    @PostMapping("/addEmployee")
    ResponseEntity<Object> createEmployee(@RequestBody EmployeeRequest request) {

        ResponceModel<EmployeeDetails> responce;
        EmployeeDetails empDetails = null;
        if (request != null) {
            try {
                empDetails = employeeService.saveEmployee(request);
                responce = new ResponceModel<>(HttpStatus.CREATED, ApplicationCnstant.USER_CREATED, empDetails);
                return ResponseEntity.ok().body(responce);
            } catch (Exception e) {
                responce = new ResponceModel<>(HttpStatus.BAD_REQUEST, e.getMessage(), empDetails);
                return ResponseEntity.ok().body(responce);
            }
        }
        responce = new ResponceModel<>(HttpStatus.BAD_REQUEST, ApplicationCnstant.BODY_ERROR, empDetails);
        return ResponseEntity.ok().body(responce);
    }




    //getAllemployee

    @GetMapping("/getAllEmpl")
    ResponseEntity<Object> getAllEmployee() {

        ResponceModel<List<EmployeeDetails>> responce;
        List<EmployeeDetails> empDetails  = employeeService.getAllEmployee();

        if(empDetails.isEmpty()){
            responce = new ResponceModel<>(HttpStatus.NOT_FOUND, ApplicationCnstant.DATA_NOT_FOUND, empDetails);
            return ResponseEntity.ok().body(responce);
        }
        responce = new ResponceModel<>(HttpStatus.FOUND, ApplicationCnstant.DATA_FOUND, empDetails);
        return ResponseEntity.ok().body(responce);
    }



    //geemployee by id

    @GetMapping("/getEmpl")
    ResponseEntity<Object> getEmployeeById(@RequestParam Integer empId) {

        ResponceModel<EmployeeDetails> responce;
        EmployeeDetails empDetails  = employeeService.getEmployeeById(empId);

        if(empDetails!=null){
            responce = new ResponceModel<>(HttpStatus.FOUND, ApplicationCnstant.DATA_FOUND, empDetails);
            return ResponseEntity.ok().body(responce);
        }
        responce = new ResponceModel<>(HttpStatus.NOT_FOUND, ApplicationCnstant.DATA_NOT_FOUND, empDetails);
        return ResponseEntity.ok().body(responce);
    }

    //getEmployee by aadhar

    @PostMapping("/getByaadhar")
    ResponseEntity<Object> getEmployeeByAadhar(@RequestBody String aadhar) {

        ResponceModel<EmployeeDetails> responce;
        EmployeeDetails empDetails  = employeeService.getEmployeeByAadhar(aadhar);

        if(empDetails!=null){
            responce = new ResponceModel<>(HttpStatus.FOUND, ApplicationCnstant.DATA_FOUND, empDetails);
            return ResponseEntity.ok().body(responce);
        }
        responce = new ResponceModel<>(HttpStatus.NOT_FOUND, ApplicationCnstant.DATA_NOT_FOUND, empDetails);
        return ResponseEntity.ok().body(responce);
    }

}
