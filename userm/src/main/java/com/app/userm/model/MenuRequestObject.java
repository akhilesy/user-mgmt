package com.app.userm.model;

import lombok.Data;

@Data
public class MenuRequestObject {
    private String menuName ;
    private String menuDes;
    private Integer parentMenuId;
    private Integer menuOrder;
    private String menuUrl;
    private String  subMenu;
    private Integer  roleId;
}
