package com.csc340.pcm.entity;

import com.csc340.pcm.entity.AbstractEntity;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
public class Prisoner extends AbstractEntity {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    public Prisoner(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Prisoner() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}
