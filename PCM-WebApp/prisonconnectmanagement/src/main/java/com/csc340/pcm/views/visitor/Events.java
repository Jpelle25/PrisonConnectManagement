package com.csc340.pcm.views.visitor;

public class Events {

    String eventName;
    String date;
    String description;

    public Events(String eventName, String date, String description) {
        this.eventName = eventName;
        this.date = date;
        this.description = description;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
