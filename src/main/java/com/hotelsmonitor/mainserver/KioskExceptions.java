package com.hotelsmonitor.mainserver;

public class KioskExceptions {

    private String Id;
    private String exType;
    private String exceptionDisc;
    private String date;
    public KioskExceptions(String exType, String exceptionDisc,String date) {
        this.exType = exType;
        this.exceptionDisc = exceptionDisc;
        this.date = date;
    }

    public String getExType() {
        return exType;
    }

    public String getExceptionDisc() {
        return exceptionDisc;
    }

    public String getDate() {
        return date;
    }
}
