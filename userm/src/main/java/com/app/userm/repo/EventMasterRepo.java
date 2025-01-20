package com.app.userm.repo;

import com.app.userm.entity.EventsMaster;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventMasterRepo  extends CrudRepository<EventsMaster,Integer> {
}
