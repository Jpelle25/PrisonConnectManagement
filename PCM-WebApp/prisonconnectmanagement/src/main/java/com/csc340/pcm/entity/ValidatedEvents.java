//Created by Marcus Harrington
//last updated 11/15/22
//This File created an object that is made when the Admin approves or denies an Org-Event
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

    /**
     * This method creates a new Validated event object to be sent back to the Organization user
     * @param orgName the name of the Organization
     * @param email the email of the Organization
     * @param phoneNumber The phone Number of the Organization. stored as a String
     * @param type The form of Organization. it can be one of a few options
     * @param eventName Name of the event that will be hosted
     * @param eventDetails a short detailing of the event
     * @param status The Status of the validated Event. the Admin can declare it Approved or Denied
     * @param comment an additional comment the Admin can declare
     */
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

    /**
     * an empty argument for Validated events
     */
    public ValidatedEvents(){

    }

    /**
     * gets the name of the organization
     * @return the name of the organization
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * this method changes the old organization name with a new one
     * @param orgName the new name that will be used
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * gets the email of the organization
     * @return the email of the organization
     */
    public String getEmail() {
        return email;
    }

    /**
     * changes the email of an organization with a new one
     * @param email the new email that will be used
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * gets the phone number of the organization
     * @return the name of the organization
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * replaces the phone number of an organization with a new one
     * @param phoneNumber the new phone number that will be used
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * gets the type of event the organization will host
     * @return the type of event
     */
    public String getType() {
        return type;
    }

    /**
     * replace the type of event an organization will host with a new one
     * @param type the new event type that will be used
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     */
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
