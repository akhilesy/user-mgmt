package com.app.userm.repo;

import com.app.userm.entity.SiteMaster;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteRepo extends CrudRepository<SiteMaster,Integer> {
}
