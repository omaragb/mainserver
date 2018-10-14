package com.hotelsmonitor.mainserver;

public class KioskExceptions {


    private String exType;
    private String exceptionDisc;
    private String date;
    private String inProgress;
    public KioskExceptions(String exType, String exceptionDisc,String date) {
        this.exType = exType;
        this.exceptionDisc = exceptionDisc;
        this.date = date;
        this.inProgress = "NotInProgress";
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
    public String getInProgress(){
        return this.inProgress;
    }
    public void setInProgress(String inProgress){
        this.inProgress = inProgress;
    }
}
