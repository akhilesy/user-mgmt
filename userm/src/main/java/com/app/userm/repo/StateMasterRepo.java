package com.app.userm.repo;

import com.app.userm.entity.StateMaster;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateMasterRepo extends CrudRepository<StateMaster,Integer> {
}
