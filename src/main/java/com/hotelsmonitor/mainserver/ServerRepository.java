package com.hotelsmonitor.mainserver;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


//MongodDB repository of servers
@Repository
public interface ServerRepository extends MongoRepository<Server,String> {
    Server getById(String id);
    Server getByName(String name);
    void deleteByName(String id);
}