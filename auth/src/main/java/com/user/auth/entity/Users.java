package com.user.auth.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users") // Specify the MongoDB collection name
public class Users {

    @Transient
    public static final String SEQUENCE_USERS = "users_sequence";

    @Id
    private String id; // MongoDB uses a String for the ID by default

    @Field("first_name")
    private String firstName;

    @Field("last_name")
    private String lastName;

    @Field("father_name")
    private String fatherName;

    @Field("email_id")
    private String emailId;

    @Field("mobile")
    private String mobile;

    @Field("address")
    private String address;

    @Field("country")
    private String country;

    @Field("state")
    private Integer state;

    @Field("district")
    private Integer district;

    @Field("password")
    private String password;

    @Field("role")
    private Integer role;

    @Field("activation_date")
    private LocalDateTime activationDate;

    @Field("expiration_date")
    private LocalDateTime expirationDate;

    @Field("login_type")
    private Integer loginType;

    @Field("created_by")
    private String createdBy;

    @Field("created_date")
    private LocalDateTime createdDate;

    @Field("updated_by")
    private String updatedBy;

    @Field("updated_date")
    private LocalDateTime updatedDate;

    @Field("is_active")
    private Boolean isActive = Boolean.TRUE;

    @Field("is_deleted")
    private Boolean isDeleted = Boolean.FALSE;


}

