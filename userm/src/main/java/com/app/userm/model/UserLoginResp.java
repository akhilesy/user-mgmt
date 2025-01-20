package com.app.userm.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class UserLoginResp {

    private String firstName;
    private String lastName;
    private String fatherName;
    private String password;
    private String mobile;
    private String state;
    private String district;
    private String block;
    private String workingArea;
    private String designation;
    private Integer roleId;
    private String roleName;

}
