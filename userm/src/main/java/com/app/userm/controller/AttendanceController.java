package com.app.userm.controller;

import com.app.userm.constant.ApplicationCnstant;
import com.app.userm.entity.Attendance;
import com.app.userm.entity.MonthalyReport;
import com.app.userm.model.AttendanceDTO;
import com.app.userm.model.AttendanceRequest;
import com.app.userm.model.ResponceModel;
import com.app.userm.service.AttandanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/attendance")
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



    @GetMapping("/salaryUpdate")
    ResponseEntity<Object> getAttendanceOfMonth(@RequestParam("atdDate") LocalDate atdDate) {

        ResponceModel<Object> responce;
        Boolean empDetails  =attandanceService.updateSalaryReport(atdDate);

        if(!empDetails){
            responce = new ResponceModel<>(HttpStatus.BAD_REQUEST, ApplicationCnstant.RECORD_NOT_UPDATED, empDetails);
            return ResponseEntity.ok().body(responce);
        }
        responce = new ResponceModel<>(HttpStatus.OK, ApplicationCnstant.RECORD_UPDATED, empDetails);
        return ResponseEntity.ok().body(responce);
    }

    @GetMapping("/getEmployeeAttendance")
    ResponseEntity<Object> getAttendanceOfEmployee(@RequestParam("atdDate") LocalDate atdDate,
                                                   @RequestParam(value = "empId", required = false) Integer empId) {

        ResponceModel<List<AttendanceDTO>> response;
        List<AttendanceDTO> empDetails = attandanceService.getAttendanceOfEmployee(empId, atdDate);

        if (empDetails.isEmpty()) {
            response = new ResponceModel<>(HttpStatus.NOT_FOUND, ApplicationCnstant.DATA_NOT_FOUND, empDetails);
            return ResponseEntity.ok().body(response);
        }
        response = new ResponceModel<>(HttpStatus.FOUND, ApplicationCnstant.DATA_FOUND, empDetails);
        return ResponseEntity.ok().body(response);
    }


    @GetMapping("/salaryReport")
    ResponseEntity<Object> getSalaryReportOfMonth(@RequestParam("atdDate") LocalDate atdDate) {

        ResponceModel< List<MonthalyReport>> responce;
        List<MonthalyReport> empDetails  =attandanceService.getSalaryReportMonthaly(atdDate);

        if(empDetails.isEmpty()){
            responce = new ResponceModel<>(HttpStatus.BAD_REQUEST, ApplicationCnstant.DATA_NOT_FOUND, empDetails);
            return ResponseEntity.ok().body(responce);
        }
        responce = new ResponceModel<>(HttpStatus.OK, ApplicationCnstant.DATA_FOUND, empDetails);
        return ResponseEntity.ok().body(responce);
    }



}


