package com.hotelsmonitor.mainserver;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TechRepository extends MongoRepository<Technician,String> {
}
