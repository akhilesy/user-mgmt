package com.user.auth.repo;


import com.user.auth.entity.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends MongoRepository<Users,Integer> {
    Users findByEmailId(String email);
}
