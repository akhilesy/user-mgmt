package com.app.userm.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "state_master")
public class StateMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "state_id", nullable = false)
    private Integer stateId;

    @Column(name = "state_name", nullable = false, length = 100)
    private String stateName;

    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "created_by", nullable = false, length = 50)
    private String createdBy;

    @Column(name = "created_date", columnDefinition = "timestamp default now()")
    private LocalDateTime createdDate = LocalDateTime.now();
}
