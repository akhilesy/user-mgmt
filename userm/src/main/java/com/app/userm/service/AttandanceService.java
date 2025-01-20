package com.app.userm.service;

import com.app.userm.entity.Attendance;
import com.app.userm.exception.CustomException;
import com.app.userm.model.AttendanceRequest;

import java.time.LocalDateTime;
import java.util.List;

public interface AttandanceService {

    Boolean saveAttandance(List<AttendanceRequest> request);

    Attendance UpdateAttandance(AttendanceRequest request);

    List<Attendance> getAttandanceOfMonth(Integer year, Integer month);

    List<Attendance> getAttandanceOfEmployee(Integer empId,Integer year, Integer month);

}
