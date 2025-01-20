package com.user.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {
    private String firstName;
    private String lastName;
    private String fatherName;
    private String emailId;
    private String mobile;
    private String address;
    private String country;
    private Integer state;
    private Integer district;
    private String state1;
    private String district1;
    private String password;
    private Integer role;


}
