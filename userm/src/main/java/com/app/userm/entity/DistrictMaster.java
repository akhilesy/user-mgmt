package com.app.userm.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "district_master")
public class DistrictMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "district_id", nullable = false)
    private Integer districtId;

    @Column(name = "district_name", nullable = false, length = 100)
    private String districtName;

    @ManyToOne
    @JoinColumn(name = "state_id", foreignKey = @ForeignKey(name = "district_master_state_id_fkey"))
    private StateMaster state;

    @Column(name = "created_by", nullable = false, length = 50)
    private String createdBy;

    @Column(name = "created_date", columnDefinition = "timestamp default now()")
    private LocalDateTime createdDate = LocalDateTime.now();
}
