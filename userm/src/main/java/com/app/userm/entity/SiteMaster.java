package com.app.userm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "site_master")
public class SiteMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer siteId;

    @Column(name = "site_name", nullable = false, length = 100)
    private String siteName;

    @Column(name = "site_description", length = 255)
    private String siteDescription; // Corrected the typo from 'siteDesription' to 'siteDescription'

    @Column(name = "owner_name", nullable = false, length = 100)
    private String ownerName;

    @Column(name = "owner_mobile", nullable = false, length = 15)
    private String ownerMobile;

    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_by", length = 50)
    private String updatedBy;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;
}
