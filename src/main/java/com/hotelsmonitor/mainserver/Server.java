package com.hotelsmonitor.mainserver;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "servers")
public class Server {
    @Id
    private String id;
    private String name;
    @Indexed(direction = IndexDirection.ASCENDING)
    private List<Kiosk> kiosks;

    protected Server(){this.kiosks = new ArrayList<>();}

    public Server(String name, List<Kiosk> kiosks) {
        this.name = name;
        this.kiosks = kiosks;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Kiosk> getKiosks() {
        return kiosks;
    }
}