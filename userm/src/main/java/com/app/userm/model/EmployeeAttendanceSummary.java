package com.app.userm.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeAttendanceSummary {
    private int employeeId;
    private int totalOtHours;
    private int totalPresentDays;
}
