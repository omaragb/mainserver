package com.hotelsmonitor.mainserver;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

// Representation of a technician in the System
@Document(collection = "technicians")
public class Technician {
    @Id
    private String id;
    private String techName;
    private List<Job> jobs;
    private String email;
    private String password;

    public Technician(String techName,String password ,String email) {
        this.techName = techName;
        this.jobs = new ArrayList<>();
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }

    public String getTechName() {
        return techName;
    }

    public List<Job> getJobs() {
        return jobs;
    }
    public void addKiosk(Job kiosk){
        jobs.add(kiosk);
    }

    public String getEmail() {
        return email;
    }

    public void removeJob(Job jIter) {
        int ind = jobs.indexOf(jIter);
        jobs.remove(ind);
    }
}
