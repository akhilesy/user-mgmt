package com.app.userm.service.impl;

import com.app.userm.entity.Attendance;
import com.app.userm.exception.CustomException;
import com.app.userm.model.AttendanceRequest;
import com.app.userm.repo.AttandanceRepo;
import com.app.userm.service.AttandanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Service
public class AttandanceServiceImpl implements AttandanceService {

    @Autowired
    private AttandanceRepo attandanceRepo;

    /**
     * @param attendanceList
     * @return
     */
    @Override
    public Boolean saveAttandance(List<AttendanceRequest> attendanceList) {
List<Attendance> listOfAttendance=new ArrayList<>();

           objectSetter(listOfAttendance,attendanceList);
           if(!listOfAttendance.isEmpty()) {
               attandanceRepo.saveAll(listOfAttendance);
               return Boolean.TRUE;
           }

        return Boolean.FALSE;
    }

    private void objectSetter(List<Attendance> listOfAttendance, List<AttendanceRequest> attendanceList) {

        for(AttendanceRequest attendanceRequest:attendanceList){
            Attendance attendance=new Attendance();
            attendance.setAttandanceDate(attendanceRequest.getAttenDate());
            attendance.setPresent(Boolean.TRUE);
            attendance.setOtHours(attendanceRequest.getOtHours());
            attendance.setEmployeeId(attendanceRequest.getEmployeeId());
            attendance.setSiteCode(attendanceRequest.getSiteCode());
            attendance.setCreatedBy("admin");
            attendance.setCreatedDate(LocalDateTime.now());
            listOfAttendance.add(attendance);

        }
    }

    /**
     * @param request
     * @return
     */
    @Override
    public Attendance UpdateAttandance(AttendanceRequest request) {

        return null;
    }

    /**
     * @param year
     * @param month
     * @return
     */
    @Override
    public List<Attendance> getAttandanceOfMonth(Integer year, Integer month) {

        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDateTime startOfMonth = yearMonth.atDay(1).atStartOfDay();

        LocalDateTime endOfMonth = yearMonth.plusMonths(1).atDay(1).atStartOfDay();
         return attandanceRepo.findByYearMonth(startOfMonth,endOfMonth);

    }

    /**
     * @param empId
     * @return
     */
    @Override
    public List<Attendance> getAttandanceOfEmployee(Integer empId,Integer year, Integer month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDateTime startOfMonth = yearMonth.atDay(1).atStartOfDay();

        LocalDateTime endOfMonth = yearMonth.plusMonths(1).atDay(1).atStartOfDay();
        return attandanceRepo.findByYearMonthOfEmployee(empId,startOfMonth,endOfMonth);

    }
}
