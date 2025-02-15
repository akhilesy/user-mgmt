package com.app.userm.service.impl;

import com.app.userm.constant.ApplicationCnstant;
import com.app.userm.entity.Attendance;
import com.app.userm.entity.MonthalyReport;
import com.app.userm.model.AttendanceDTO;
import com.app.userm.model.AttendanceRequest;
import com.app.userm.model.EmployeeAttendanceSummary;
import com.app.userm.repo.AttandanceRepo;
import com.app.userm.repo.MonthalyReportRepo;
import com.app.userm.service.AttandanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AttandanceServiceImpl implements AttandanceService {

    @Autowired
    private AttandanceRepo attandanceRepo;

    @Autowired
    private MonthalyReportRepo monthlyRepo;

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
            attendance.setAttandanceDate(attendanceRequest.getAttenDate().atTime(0,0,0));
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
     * @param atdDate
     * @return
     */
    @Override
    public List<Attendance> getAttandanceOfMonth( LocalDate atdDate) {

        YearMonth yearMonth = YearMonth.from(atdDate);
        LocalDateTime startOfMonth = yearMonth.atDay(1).atStartOfDay();
        LocalDateTime endOfMonth = yearMonth.atEndOfMonth().atTime(23, 59, 59, 999999999);
        return attandanceRepo.findByYearMonth(startOfMonth, endOfMonth);

    }

    /**
     * @param empId
     * @return
     */
    @Override
    public List<AttendanceDTO> getAttendanceOfEmployee(Integer empId, LocalDate atdDate) {
        // Get the start and end of the month
        YearMonth yearMonth = YearMonth.from(atdDate);
        LocalDateTime startOfMonth = yearMonth.atDay(1).atStartOfDay();
        LocalDateTime endOfMonth = yearMonth.atEndOfMonth().atTime(23, 59, 59, 999999999);
        // Fetch attendance records
        List<Object[]> results = attandanceRepo.findByYearMonthOfEmployee(empId, startOfMonth, endOfMonth);

        // Convert result list to DTOs using Stream API
        return results.stream()
                .map(row -> new AttendanceDTO(
                        (String) row[0],                    // fullName
                        (String) row[1],                    // siteName
                        ((Timestamp) row[2]).toLocalDateTime(), // attendanceDate
                        (Boolean) row[3],                   // isPresent
                        (Integer) row[4]                    // otHours
                ))
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateSalaryReport(LocalDate atdDate) {
        List<Attendance> attofmonth= getAttandanceOfMonth(atdDate);
        List<EmployeeAttendanceSummary> attendanceSummary = attofmonth.stream()
                .collect(Collectors.groupingBy(
                        Attendance::getEmployeeId, // Grouping by employeeId
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                records -> {
                                    int totalOtHours = records.stream().mapToInt(Attendance::getOtHours).sum();
                                    int totalPresentDays = (int)records.stream().filter(Attendance::isPresent).count();
                                    return new EmployeeAttendanceSummary(
                                            records.get(0).getEmployeeId().intValue(), // Employee ID
                                            totalOtHours,
                                            totalPresentDays
                                    );
                                }
                        )
                ))
                .values()                      // Get Collection<EmployeeAttendanceSummary>
                .stream()                      // Convert to Stream
                .collect(Collectors.toList()); // Collect into List

        YearMonth yearMonth = YearMonth.from(atdDate);
        LocalDate startOfMonth = yearMonth.atDay(1);               // First day of the month
        LocalDate endOfMonth = yearMonth.atEndOfMonth();
        List<MonthalyReport> monmthalyReportList= monthlyRepo.findBySalaryMonth(startOfMonth,endOfMonth);
        List<MonthalyReport> savedReport=null;

            if(monmthalyReportList.isEmpty()){
               List<MonthalyReport> report=new ArrayList<>();
               createReportList(report,attendanceSummary,atdDate);
               savedReport= (List<MonthalyReport>) monthlyRepo.saveAll(report);
                return Boolean.TRUE;
            }else {
                Map<Integer, EmployeeAttendanceSummary> sourceList = attendanceSummary.stream().collect(Collectors.toMap(EmployeeAttendanceSummary::getEmployeeId, Function.identity()));

                monmthalyReportList.forEach(report -> {
                        EmployeeAttendanceSummary summery = sourceList.get(report.getEmpId());
                        if (summery != null) {
                            report.setTotalPresent(summery.getTotalPresentDays());
                            report.setOverTime(summery.getTotalOtHours());
                            report.setUpdatedBy("update");
                            report.setUpdatedDate(LocalDateTime.now());
                        }else{

                        }
                    });
                    monthlyRepo.saveAll(monmthalyReportList);
                    return Boolean.TRUE;

            }


    }

    @Override
    public List<MonthalyReport> getSalaryReportMonthaly(LocalDate atdDate) {
        YearMonth yearMonth = YearMonth.from(atdDate);
        LocalDate startOfMonth = yearMonth.atDay(1);               // First day of the month
        LocalDate endOfMonth = yearMonth.atEndOfMonth();
        return monthlyRepo.findBySalaryMonth(startOfMonth,endOfMonth);
    }

    private void createReportList( List<MonthalyReport> report, List<EmployeeAttendanceSummary> summaryList,LocalDate atdDate) {
        for(EmployeeAttendanceSummary summ:summaryList){
            MonthalyReport rpt=new MonthalyReport();
            rpt.setPresent( summ.getTotalPresentDays());
            rpt.setEmpId(summ.getEmployeeId());
            rpt.setOverTime(summ.getTotalOtHours());
            rpt.setCreatedDate(LocalDateTime.now());
            rpt.setCreatedBy(ApplicationCnstant.BASE_USER);
            rpt.setSalaryMonth(atdDate);
            report.add(rpt);
        }
    }

}
