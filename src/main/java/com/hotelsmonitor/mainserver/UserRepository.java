package com.hotelsmonitor.mainserver;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    User findUserByName(String name);
    User findUserByEmail(String email);
    void deleteByEmail(String email);
}
