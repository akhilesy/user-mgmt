package com.app.userm.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceRequest {


    private Long employeeId;


 private LocalDate attenDate;

    private Integer siteCode;

    private boolean isPresent;


    private int otHours;

}
