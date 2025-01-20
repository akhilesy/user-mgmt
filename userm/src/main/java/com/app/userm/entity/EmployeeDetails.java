package com.app.userm.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "emp_details")
public class EmployeeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id", nullable = false, length = 10)
    private Integer empId;

    @NotNull(message = "Full name cannot be null")
    @Size(max = 50, message = "Full name must not exceed 50 characters")
    @Column(name = "full_name", nullable = false, length = 50)
    private String fullName;

    @NotNull(message = "Date of birth cannot be null")
    @Column(name = "date_of_birth", nullable = false)
    private LocalDateTime dateOfBirth;

    @NotNull(message = "Phone number cannot be null")
    @Size(max = 15, message = "Phone number must not exceed 15 characters")
    @Pattern(regexp = "^\\+?[0-9]+", message = "Phone number must contain only digits and optional '+' sign")
    @Column(name = "phone", nullable = false, length = 15)
    private String phone;

    @NotNull(message = "Aadhar cannot be null")
    @Size(max = 15, message = "Aadhar must not exceed 15 characters")
    @Column(name = "aadhar", nullable = false, length = 15)
    private String aadhar;

    @NotNull(message = "Email cannot be null")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Invalid email format")
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @NotNull(message = "Rate cannot be null")
    @Column(name = "rate", nullable = false)
    private Integer rate;

    @NotNull(message = "Address cannot be null")
    @Size(max = 255, message = "Address must not exceed 255 characters")
    @Column(name = "address", nullable = false, length = 255)
    private String address;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = Boolean.TRUE;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = Boolean.FALSE;

    // Metadata fields
    @NotNull(message = "Created by cannot be null")
    @Size(max = 50, message = "Created by must not exceed 50 characters")
    @Column(name = "created_by", nullable = false, length = 50)
    private String createdBy;

    @NotNull(message = "Created date cannot be null")
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Size(max = 50, message = "Updated by must not exceed 50 characters")
    @Column(name = "updated_by", length = 50)
    private String updatedBy;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
}