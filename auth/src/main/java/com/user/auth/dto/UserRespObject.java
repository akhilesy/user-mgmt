package com.user.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRespObject {
    private String fullName;
    private String fatherName;
    private String emailId;
    private String mobile;
    private String address;
    private String country;
    private String state;
    private String district;
    private String password;
    private String role;
    private Boolean isActive;
    private boolean isDeleted;
    private LocalDateTime expirationDate;
}
