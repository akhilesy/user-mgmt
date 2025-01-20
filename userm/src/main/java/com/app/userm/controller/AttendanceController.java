package com.app.userm.controller;

import com.app.userm.constant.ApplicationCnstant;
import com.app.userm.entity.Attendance;
import com.app.userm.model.AttendanceRequest;
import com.app.userm.model.ResponceModel;
import com.app.userm.service.AttandanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("attendance")
public class AttendanceController {

    @Autowired
    AttandanceService attandanceService;

    @PostMapping("/add")
    ResponseEntity<Object> createEmployee(@RequestBody List<AttendanceRequest> request) {

        ResponceModel<Boolean> responce;

        if (request.isEmpty()) {
            responce = new ResponceModel<>(HttpStatus.BAD_REQUEST, ApplicationCnstant.BODY_ERROR);
            return ResponseEntity.ok().body(responce);
        }

        Boolean empDetails = attandanceService.saveAttandance(request);
        if(empDetails){
            responce = new ResponceModel<>(HttpStatus.OK, ApplicationCnstant.ATTENDANCE_CREATED, empDetails);
            return ResponseEntity.ok().body(responce);
        }
        responce = new ResponceModel<>(HttpStatus.BAD_REQUEST, ApplicationCnstant.BAD_REQUEST, empDetails);
        return ResponseEntity.ok().body(responce);

        }


    @GetMapping("/getAttendance")
    ResponseEntity<Object> getAttendanceOfMonth(@RequestParam Integer year, @RequestParam Integer month) {

        ResponceModel<List<Attendance>> responce;
        List<Attendance> empDetails  =attandanceService.getAttandanceOfMonth(year,month);

        if(empDetails.isEmpty()){
            responce = new ResponceModel<>(HttpStatus.NOT_FOUND, ApplicationCnstant.DATA_NOT_FOUND, empDetails);
            return ResponseEntity.ok().body(responce);
        }
        responce = new ResponceModel<>(HttpStatus.FOUND, ApplicationCnstant.DATA_FOUND, empDetails);
        return ResponseEntity.ok().body(responce);
    }

    @GetMapping("/getEmployeeAttendance")
    ResponseEntity<Object> getAttendanceOfEmployee(@RequestParam Integer year, @RequestParam Integer month,@RequestParam Integer empId) {

        ResponceModel<List<Attendance>> responce;
        List<Attendance> empDetails  =attandanceService.getAttandanceOfEmployee(empId,year,month);

        if(empDetails.isEmpty()){
            responce = new ResponceModel<>(HttpStatus.NOT_FOUND, ApplicationCnstant.DATA_NOT_FOUND, empDetails);
            return ResponseEntity.ok().body(responce);
        }
        responce = new ResponceModel<>(HttpStatus.FOUND, ApplicationCnstant.DATA_FOUND, empDetails);
        return ResponseEntity.ok().body(responce);
    }

    }


