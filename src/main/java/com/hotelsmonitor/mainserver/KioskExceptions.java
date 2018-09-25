package com.hotelsmonitor.mainserver;

public class KioskExceptions {

    private ExType exType;
    private String exceptionDisc;
    private String date;
    public KioskExceptions(ExType exType, String exceptionDisc,String date) {
        this.exType = exType;
        this.exceptionDisc = exceptionDisc;
        this.date = date;
    }

    public ExType getExType() {
        return exType;
    }

    public String getExceptionDisc() {
        return exceptionDisc;
    }

    public String getDate() {
        return date;
    }
}
enum ExType{YELLOW,RED}