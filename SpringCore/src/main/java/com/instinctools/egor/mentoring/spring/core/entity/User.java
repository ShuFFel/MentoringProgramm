package com.instinctools.egor.mentoring.spring.core.entity;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class User {
    private String id;
    private String userName;
    private Date dateOfBirth;


    public User() {
        id = UUID.randomUUID().toString();
    }

    public User(final String userName, final Date dateOfBirth) {
        this.userName = userName;
        this.dateOfBirth = new Date(dateOfBirth.getTime());
    }

    public void addBook(final Book book) {
        book.setOwner(this);
    }

    public void removeBook(final Book book) {
        book.setOwner(null);
    }

    public String getId() {
        return this.id;
    }

    public Date getDateOfBirth() {
        return new Date(this.dateOfBirth.getTime());
    }

    public String getUserName() {
        return this.userName;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public void setDateOfBirth(final Date dateOfBirth) {
        this.dateOfBirth = new Date(dateOfBirth.getTime());
    }

    @Override
    public String toString() {
        return String.format("id: %s %nname: %s %ndate_of_birth: %s", id, userName, dateOfBirth);
    }
}
