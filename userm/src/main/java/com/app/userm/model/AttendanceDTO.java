package com.app.userm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDTO {

    private String fullName;
    private String siteName;
    private LocalDateTime attandanceDate;
    private  boolean isPresent;
    private Integer otHours;
}
