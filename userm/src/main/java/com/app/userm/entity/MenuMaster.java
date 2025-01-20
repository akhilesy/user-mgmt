package com.app.userm.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name ="menu_master" )
public class MenuMaster {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer menuId;

    @Column(name = "menu_name", length = 50)
  private String menuName;

    @NotNull(message = "Menu description is required")
    @Column(name = "menu_desc", length = 100)
    private String menuDes;
    @Column(name = "parent_menu_id")
    private Integer parentMenuId;

    @Column(name = "menu_order")
    private Integer menuOrder;

    @NotNull(message = "Menu URL is required")
    @Column(name = "menu_url", length = 100)
    private String menuUrl;

    // Relationship with Role entity
    @ManyToOne
    @JoinColumn(name = "roleId", referencedColumnName = "roleId", nullable = false)
    private RoleMaster role;

    @Column(name = "sub_menu")
    private String subMenu;

    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "updated_by", length = 50)
    private String updatedBy;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;
}
