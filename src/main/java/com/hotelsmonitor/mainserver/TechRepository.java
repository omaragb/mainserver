package com.hotelsmonitor.mainserver;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.concurrent.atomic.AtomicBoolean;

@Repository
public interface TechRepository extends MongoRepository<Technician,String> {
    Technician getByTechName();
    Technician getById(String techID);

    Technician findByEmail(String email);
}
