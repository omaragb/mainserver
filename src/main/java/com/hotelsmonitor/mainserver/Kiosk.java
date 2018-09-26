package com.hotelsmonitor.mainserver;

import java.util.List;

public class Kiosk {
    private String Id;
    private String macAddress;
    private String hotelName;
    private List<Devices> devices;
    private boolean Good;
    private String serverID;
    private List<KioskExceptions> exceptions;

    public Kiosk(String macAddress, String hotelName, List<Devices> devices, List<KioskExceptions> exceptions) {
        this.macAddress = macAddress;
        this.hotelName = hotelName;
        this.devices = devices;
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

    public String getId() {
        return Id;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public String getHotelName() {
        return hotelName;
    }

    public List<Devices> getDevices() {
        return devices;
    }

    public boolean isGood() {
        return Good;
    }

    public List<KioskExceptions> getExceptions() {
        return exceptions;
    }

    public String getServerID() {
        return serverID;
    }
}
