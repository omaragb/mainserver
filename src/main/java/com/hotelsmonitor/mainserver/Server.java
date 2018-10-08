package com.hotelsmonitor.mainserver;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

// Local Server instance
@Document(collection = "servers")
public class Server {
    @Id
    private String Id;
    private String name; // IP of the server
    @Indexed(direction = IndexDirection.ASCENDING)
    private List<Kiosk> kiosks; // list of the kiosks connected t the server
    private String status;
    protected Server(){this.kiosks = new ArrayList<>();}

    public Server(String name, List<Kiosk> kiosks) {
        this.name = name;
        this.kiosks = kiosks;
        this.status = "GREEN";
        for(Kiosk k : kiosks){ // represent the Server Status according to the kiosks status
                for(KioskExceptions ke : k.getExceptions())
                    if(ke.getExType().equals("YELLOW"))
                        this.status = "YELLOW";
                    else if (ke.getExType().equals("RED")) {
                        this.status = "RED";
                        break;
                    }

        }
    }

    public String getStatus() {
        return status;
    }

    public String getId() {
        return Id;
    }
    public String getName() {
        return name;
    }

    public List<Kiosk> getKiosks() {
        return kiosks;
    }

    public Kiosk getKiosk(String macAddress) {
        for(Kiosk k : this.kiosks)
            if(k.getMacAddress().equals(macAddress))
                return k;
        return null;
    }
    // this method updates the server status
    // when a prblem Type RED in at least one of the kiosks then the server status must be red
    // when we have no RED problems but a YELLOW problem the server status must be YELLOW
    // otherwise its Green
    public void setStatus() {
        boolean red = false,yellow = false;
        for(Kiosk k : this.kiosks)
            if(!k.isGood()){
                for(KioskExceptions ke : k.getExceptions()) {
                    if (ke.getExType().equals("RED")) {
                        red = true;
                        break;
                    }
                    if (ke.getExType().equals("YELLOW"))
                        yellow = true;
                }

            }
            if(red) {
                this.status = "RED";
                return;
            }
            if(yellow){
                this.status="YELLOW";
                return;
            }
            this.status = "GREEN";
    }
}
