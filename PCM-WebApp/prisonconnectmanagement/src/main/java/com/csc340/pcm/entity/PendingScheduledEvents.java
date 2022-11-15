package com.csc340.pcm.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class PendingScheduledEvents extends AbstractEntity{

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

    @NotNull
    private String startTime = "";

    @NotNull
    private String endTime = "";

    public PendingScheduledEvents() {
    }

    public PendingScheduledEvents(String organizationName, String organizationEmail, String organizationPhoneNumber,
                                  String organizationType, String eventName, String eventDetails, String status,
                                  String comment, String startTime, String endTime) {
        this.organizationName = organizationName;
        this.organizationEmail = organizationEmail;
        this.organizationPhoneNumber = organizationPhoneNumber;
        this.organizationType = organizationType;
        this.eventName = eventName;
        this.eventDetails = eventDetails;
        this.status = status;
        this.comment = comment;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
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

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
