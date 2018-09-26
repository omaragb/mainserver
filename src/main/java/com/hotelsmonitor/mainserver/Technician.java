package com.hotelsmonitor.mainserver;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "technicians")
public class Technician {
    @Id
    private String Id;
    private String techName;
    private List<Kiosk> jobs;
    private String email;

    public Technician(String techName) {
        this.techName = techName;
        this.jobs = new ArrayList<>();
    }

    public String getId() {
        return Id;
    }

    public String getTechName() {
        return techName;
    }

    public List<Kiosk> getJobs() {
        return jobs;
    }
    public void addKiosk(Kiosk kiosk){
        jobs.add(kiosk);
    }
}
