package com.user.auth.repo;

import com.user.auth.entity.RoleMaster;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<RoleMaster,Integer> {
}
