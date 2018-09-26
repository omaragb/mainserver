package com.hotelsmonitor.mainserver;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechRepository extends MongoRepository<Technician,String> {
    Technician getByTechName();
}
