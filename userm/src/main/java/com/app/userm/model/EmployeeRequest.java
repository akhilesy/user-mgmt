package com.app.userm.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {


    @NotNull(message = "name name cannot be null")
    @Size(max = 50, message = "name name must not exceed 50 characters")
    private String fullName;


    @NotNull(message = "Date of birth cannot be null")
    private LocalDateTime dateOfBirth;


    @NotNull(message = "Phone number cannot be null")
    @Size(max = 15, message = "Phone number must not exceed 15 characters")
    @Pattern(regexp = "^\\+?[0-9]+", message = "Phone number must contain only digits and optional '+' sign")
    private String phone;


    private String email;


    private Integer rate;


    @NotNull(message = "Address cannot be null")
    @Size(max = 255, message = "Address must not exceed 255 characters")
    private String address;

    @NotNull(message = "Aadhar cannot be null")
    @Size(max = 12, message = "Aadhar must not exceed 255 characters")
    private String aadharNumber;
}
