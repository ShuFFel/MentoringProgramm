package com.instinctools.egor.mentoring.web.core.entity;


import java.util.Date;
import java.util.UUID;

public class User {
    private String id;
    private String userName;
    private Date dateOfBirth;

    public User(){
        id = UUID.randomUUID().toString();
    }
    public void addBook(Book book){
        book.setOwner(this);
    }

    public void removeBook(Book book){
        book.setOwner(null);
    }

    public String getId(){
        return this.id;
    }

    public Date getDateOfBirth(){
        return this.dateOfBirth;
    }

    public String getUserName(){
        return this.userName;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setUserName(String user_name){
        this.userName = user_name;
    }

    public void setDateOfBirth(Date dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return String.format("id: %s \nname: %s \ndate_of_birth: %s \n", id, userName, dateOfBirth);
    }
}
