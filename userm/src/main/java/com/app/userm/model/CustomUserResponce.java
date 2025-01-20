package com.app.userm.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomUserResponce {
    private int id;
    private String name;
    private String fatherName;
    private String emailId;
    private String mobile;
    private String address;
    private String country;
    private String state;
    private String district;
    private String block;
    private String currAddress;
    private String currState;
    private String currDistrict;
    private String currBlock;
    private String workingArea;
    private String designation;
    private String role;

}
