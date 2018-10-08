package com.hotelsmonitor.mainserver;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


//this is a job instance which can be assigned to a technician
@JsonDeserialize(as = Job.class)
public class Job {
    private String macAddress; // the mac Address of the machine to fix
    private String problem; // Problem Code
    private String date; // When the Technician fixed the problem

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    @Override
    public String toString() {
        return "Job{" +
                "macAddress='" + macAddress + '\'' +
                ", problem='" + problem + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public Job(String macAddress, String problem, String date) {
        this.macAddress = macAddress;
        this.problem = problem;
        this.date = date;
    }

    public String getMacAdd() {
        return macAddress;
    }

    public String getProblem() {
        return problem;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Job job = (Job) o;

        if (macAddress != null ? !macAddress.equals(job.macAddress) : job.macAddress != null) return false;
        if (problem != null ? !problem.equals(job.problem) : job.problem != null) return false;
        return date != null ? date.equals(job.date) : job.date == null;
    }

    @Override
    public int hashCode() {
        int result = macAddress != null ? macAddress.hashCode() : 0;
        result = 31 * result + (problem != null ? problem.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
