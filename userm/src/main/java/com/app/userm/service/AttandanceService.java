package com.app.userm.service;

import com.app.userm.entity.Attendance;
import com.app.userm.entity.MonthalyReport;
import com.app.userm.model.AttendanceDTO;
import com.app.userm.model.AttendanceRequest;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AttandanceService {

    Boolean saveAttandance(List<AttendanceRequest> request);

    Attendance UpdateAttandance(AttendanceRequest request);

    List<Attendance> getAttandanceOfMonth( LocalDate atdDate);

    List<AttendanceDTO> getAttendanceOfEmployee(Integer empId , LocalDate atdDate);

    boolean updateSalaryReport(LocalDate atdDate);

    List<MonthalyReport> getSalaryReportMonthaly(LocalDate atdDate);
}
