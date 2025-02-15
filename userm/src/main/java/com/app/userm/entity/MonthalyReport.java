package com.app.userm.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name ="monthaly_report")
public class MonthalyReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY strategy
    private Long id;
    @Column(name = "empId")
    private Integer empId;
    @Column(name = "present")
    private Integer present;
    @Column(name = "overTime")
    private Integer overTime;
    @Column(name = "totalPresent")
    private double totalPresent;
    @Column(name = "rate")
    private Integer rate;
    @Column(name = "salary")
    private double salary;
    @Column(name = "advance")
    private Integer advance;
    @Column(name = "remaining")
    private double remaining;
    @Column(name = "salary_month")
    private LocalDate salaryMonth;
    // Metadata fields
    @Column(name = "created_by", nullable = false, length = 50)
    private String createdBy;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "updated_by", length = 50)
    private String updatedBy;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

}
