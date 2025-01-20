package com.app.userm.repo;

import com.app.userm.entity.DistrictMaster;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictMasterRepo  extends CrudRepository<DistrictMaster,Integer> {

    List<DistrictMaster> findByState_StateId(Integer stateId);
}
