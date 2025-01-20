package com.app.userm.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "social_info") // Proper table name
public class SocialInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Event name cannot be null")
    @Size(min = 2, max = 100, message = "Event name must be between 2 and 100 characters")
    @Column(name = "event_name", length = 100)
    private String eventName;

    @NotNull(message = "Event description cannot be null")
    @Size(min = 10, max = 500, message = "Event description must be between 10 and 500 characters")
    @Column(name = "event_desc", length = 500)
    private String eventDesc;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "updated_by", length = 50)
    private String updatedBy;

    @Column(name = "updated_date")
    private Date updatedDate;

    @NotNull
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = Boolean.TRUE;

    @NotNull
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = Boolean.FALSE;
}
