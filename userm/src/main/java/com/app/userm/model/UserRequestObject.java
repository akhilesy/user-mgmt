package com.app.userm.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class UserRequestObject {

    @NotBlank(message = "First name is required")
    @Pattern(regexp = "^[A-Za-z]{1,50}$", message = "First name must contain only letters and be between 1 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Pattern(regexp = "^[A-Za-z]{1,50}$", message = "Last name must contain only letters and be between 1 and 50 characters")
    private String lastName;

    @NotBlank(message = "Father's name is required")
    @Pattern(regexp = "^[A-Za-z ]{1,100}$", message = "Father's name must contain only letters and spaces and be between 1 and 100 characters")
    private String fatherName;

    @NotBlank(message = "Email ID is required")
    @Email(message = "Email ID must be a valid email format")
    private String emailId;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be exactly 10 digits")
    private String mobile;

    @NotBlank(message = "Address is required")
    @Size(max = 100, message = "Address must be less than 100 characters")
    private String address;

    @NotBlank(message = "Country is required")
    @Pattern(regexp = "^[A-Za-z ]{1,20}$", message = "Country must contain only letters and spaces and be between 1 and 20 characters")
    private String country;

    @NotNull(message = "State is required")
    @Min(value = 1, message = "State must be a positive number")
    private Integer state;

    @NotNull(message = "District is required")
    @Min(value = 1, message = "District must be a positive number")
    private Integer district;

    @NotBlank(message = "Block is required")
    @Pattern(regexp = "^[A-Za-z ]{1,50}$", message = "Block must contain only letters and spaces and be between 1 and 50 characters")
    private String block;

    @NotBlank(message = "Current address is required")
    @Size(max = 100, message = "Current address must be less than 100 characters")
    private String currAddress;


    @NotNull(message = "Current state is required")
    @Min(value = 1, message = "Current state must be a positive number")
    private Integer currState;

    @NotNull(message = "Current district is required")
    @Min(value = 1, message = "Current district must be a positive number")
    private Integer currDistrict;

    @NotBlank(message = "Current block is required")
    @Pattern(regexp = "^[A-Za-z ]{1,50}$", message = "Current block must contain only letters and spaces and be between 1 and 50 characters")
    private String currBlock;

    @NotBlank(message = "Password1 is required")
    @Size(min = 6, max = 20, message = "Password must be between 8 and 20 characters")
    private String password1;

    @NotBlank(message = "Password2 is required")
    @Size(min = 6, max = 20, message = "Password must be between 8 and 20 characters")
    private String password2;

    @NotBlank(message = "Working area is required")
    @Size(max = 100, message = "Working area must be less than 100 characters")
    private String workingArea;

    @NotBlank(message = "Designation is required")
    @Pattern(regexp = "^[A-Za-z ]{1,50}$", message = "Designation must contain only letters and spaces and be between 1 and 50 characters")
    private String designation;

}
