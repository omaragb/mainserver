package com.hotelsmonitor.mainserver;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServerRepository extends MongoRepository<Server,String> {
    Server getById(String id);
}