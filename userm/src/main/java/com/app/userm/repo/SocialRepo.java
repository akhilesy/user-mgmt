package com.app.userm.repo;

import com.app.userm.entity.SocialInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialRepo  extends CrudRepository<SocialInfo,Integer> {
}
