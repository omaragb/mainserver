package com.hotelsmonitor.mainserver;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
    User findUserByName(String name);
}
