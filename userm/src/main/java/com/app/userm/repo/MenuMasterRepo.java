package com.app.userm.repo;

import com.app.userm.entity.MenuMaster;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMasterRepo extends CrudRepository<MenuMaster , Integer> {


}
