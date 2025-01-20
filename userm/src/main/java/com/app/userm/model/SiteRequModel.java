package com.app.userm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiteRequModel {

    private String siteName;
    private String workDescription;
    private String  ownerName;
    private String ownerMobile;
}
