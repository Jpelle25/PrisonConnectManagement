//created by Marcus harrington
//last updated 11/14/22
//A dummy object that was used to test vaadin applications
package com.csc340.pcm.entity;

public class Request {
    private String name;
    private Boolean isOrg;
    private String date;

    /**
     * this method creates a new request object
     *
     * @param name  is the name of the person or orgonization
     * @param isOrg lets the user know if the request is an orgonizarion or not true for it is an orgonization and
     *              false if it is a visitor
     * @param date  the date in which the requestee wishes to go host or participate in an event at the prison
     */
    public Request(String name, Boolean isOrg, String date) {
        this.name = name;
        this.isOrg = isOrg;
        this.date = date;
    }

    /**
     * this method replaces the default constructor with  valid one
     */
    public Request() {
        this.name = "Jane Doe";
        this.isOrg = false;
        this.date = "07-11-2022";
    }

    /**
     * this method replaces the current name of the request with a new one
     *
     * @param name is the new name of the orgonization or person
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * this method will return the name of the request
     *
     * @return the requester's name as a String
     */
    public String getName() {
        return this.name;
    }

    /**
     * this method replaces the date of the request to a new one
     *
     * @param date the new date that the request will take place in
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * this method gives the date of the request in the form of a string
     *
     * @return the date of the request
     */
    public String getDate() {
        return this.date;
    }

    /**
     * this method reads if the type of request is an orgonization or not and returns a charecter
     *
     * @return a Y for Yes if the resquest is from an orgonization
     * return a N for No if the request is from a visior
     */
    public char getRequestType() {
        if (isOrg == true) {
            return 'Y';
        }
        return 'N';
    }

}
