package com.csc340.pcm.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class ValidatedEvents extends AbstractEntity {
    @NotNull
    private String orgName = "";
    @NotNull
    private String email = "";
    @NotNull
    private String phoneNumber = "";
    @NotNull
    private String type = "";
    @NotNull
    private String eventName = "";
    @NotNull
    private String eventDetails = "";
    @NotNull
    private String status = "";
    @NotNull
    private String comment = "";

    public ValidatedEvents(String orgName, String email, String phoneNumber, String type, String eventName, String eventDetails, String status, String comment) {
        this.orgName = orgName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.type = type;
        this.eventName = eventName;
        this.eventDetails = eventDetails;
        this.status = status;
        this.comment = comment;
    }
    public ValidatedEvents(){

    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(String eventDetails) {
        this.eventDetails = eventDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
