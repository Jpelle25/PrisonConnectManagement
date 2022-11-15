package com.csc340.pcm.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class ValidatedEvents extends AbstractEntity {
    @NotNull
    private String organizationName = "";
    @NotNull
    private String organizationEmail = "";
    @NotNull
    private String organizationPhoneNumber = "";
    @NotNull
    private String organizationType = "";
    @NotNull
    private String eventName = "";
    @NotNull
    private String eventDetails = "";
    @NotNull
    private String status = "";
    @NotNull
    private String comment = "";

    public ValidatedEvents(String organizationName, String organizationEmail, String organizationPhoneNumber,
                           String organizationType, String eventName, String eventDetails, String status, String comment) {
        this.organizationName = organizationName;
        this.organizationEmail = organizationEmail;
        this.organizationPhoneNumber = organizationPhoneNumber;
        this.organizationType = organizationType;
        this.eventName = eventName;
        this.eventDetails = eventDetails;
        this.status = status;
        this.comment = comment;
    }
    public ValidatedEvents(){

    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationEmail() {
        return organizationEmail;
    }

    public void setOrganizationEmail(String organizationEmail) {
        this.organizationEmail = organizationEmail;
    }

    public String getOrganizationPhoneNumber() {
        return organizationPhoneNumber;
    }

    public void setOrganizationPhoneNumber(String organizationPhoneNumber) {
        this.organizationPhoneNumber = organizationPhoneNumber;
    }

    public String getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(String organizationType) {
        this.organizationType = organizationType;
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
