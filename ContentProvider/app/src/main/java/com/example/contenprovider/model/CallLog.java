package com.example.contenprovider.model;

import java.util.Date;

public class CallLog {
    private String number;
    private String date;
    private String duration ;
    private String type;

    public CallLog(String number, String date, String duration, String type) {
        this.number = number;
        this.date = date;
        this.duration = duration;
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
