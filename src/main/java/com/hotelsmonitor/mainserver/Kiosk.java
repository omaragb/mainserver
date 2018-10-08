package com.hotelsmonitor.mainserver;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "servers")
public class Kiosk {
    private String macAddress;
    private String hotelName;
    private boolean Good;
    private String serverID;
    //Kiosk Inctance
    private List<KioskExceptions> exceptions;

    public Kiosk(String macAddress, String hotelName, List<KioskExceptions> exceptions) {
        this.macAddress = macAddress;
        this.hotelName = hotelName;
        this.exceptions = exceptions;
        if(exceptions.isEmpty())
            this.Good = true;
        else
            this.Good = false;
    }
    protected Kiosk(){}


    public void setExceptions(List<KioskExceptions> exceptions) {
        this.exceptions = exceptions;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public String getHotelName() {
        return hotelName;
    }



    public boolean isGood() {
        return Good;
    }

    public List<KioskExceptions> getExceptions() {
        return exceptions;
    }

    public void setServerID(String serverID) {
        this.serverID = serverID;
    }

    public String getServerID() {
        return serverID;
    }
}
