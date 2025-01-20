package com.app.userm.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(name = "attandance_date", nullable = false)
    private LocalDateTime attandanceDate;

    @Column(name = "site_code", nullable = false, length = 100)
    private String siteCode;

    @Column(name = "is_present", nullable = false)
    private boolean isPresent;

    @Column(name = "ot_hours", nullable = false)
    private int otHours;

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
