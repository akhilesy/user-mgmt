package com.user.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "role_master")
public class RoleMaster {

    @Transient
    public static final String SEQUENCE_ROLE = "role_sequence";
    @Id
    private String id;


    @Field("role_name")
    private String roleName;

    @Field("role_desc")
    private String roleDesc;


    @Field("created_by")
    private String createdBy;

    @Getter
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
