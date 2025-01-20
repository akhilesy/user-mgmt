package com.user.auth.services;

import com.user.auth.dto.RoleDto;
import com.user.auth.entity.RoleMaster;

import java.util.List;

public interface MasterService {

    public String generateSequence(String seqName);
    public RoleMaster addRole(RoleDto roleDto);

    public List<RoleMaster> getAllRole();

}
