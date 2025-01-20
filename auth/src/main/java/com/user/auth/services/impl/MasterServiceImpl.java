package com.user.auth.services.impl;

import com.user.auth.constant.ApplicationConstants;
import com.user.auth.dto.RoleDto;
import com.user.auth.entity.DatabaseSequence;
import com.user.auth.entity.RoleMaster;
import com.user.auth.repo.RoleRepository;
import com.user.auth.services.MasterService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Service
public class MasterServiceImpl implements MasterService {

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public String generateSequence(String seqName) {
        DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : "1";
    }






    @Override
    public RoleMaster addRole(RoleDto roleDto) {
        RoleMaster roleMaster=new RoleMaster();
        createRoleObject(roleMaster,roleDto);
       return roleRepo.save(roleMaster);

    }

    private void createRoleObject(RoleMaster roleMaster, RoleDto roleDto) {
       roleMaster.setId(generateSequence(RoleMaster.SEQUENCE_ROLE));
        roleMaster.setRoleName(roleDto.getRoleName());
        roleMaster.setRoleDesc(roleDto.getRoleDesc());
        roleMaster.setCreatedBy(ApplicationConstants.ADMIN);
        roleMaster.setCreatedDate(LocalDateTime.now());

    }

    @Override
    public List<RoleMaster> getAllRole() {

        return roleRepo.findAll();
    }
}
