package com.csc340.pcm.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class Event extends AbstractEntity{

    @NotEmpty
    private String orgName = "";

    @NotEmpty
    private String orgEmail = "";

    @NotEmpty
    private String orgPhoneNo = "";

    @NotEmpty
    private String orgType = "";

    @NotEmpty
    private String eventName = "";

    @NotEmpty
    private String eventDetails = "";

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgEmail() {
        return orgEmail;
    }

    public void setOrgEmail(String orgEmail) {
        this.orgEmail = orgEmail;
    }

    public String getOrgPhoneNo() {
        return orgPhoneNo;
    }

    public void setOrgPhoneNo(String orgPhoneNo) {
        this.orgPhoneNo = orgPhoneNo;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
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
}
