package com.instinctools.egor.mentoring.JPA.entity;

import javax.persistence.*;
import java.util.Date;


@Entity(name = "Account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    public Account() {
    }

    public Account(String nickname, Date dateOfBirth) {
        this.nickname = nickname;
        this.dateOfBirth = dateOfBirth;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString(){
        return String.format("Id: %s \nNickname: %s \nDate of birth: %s\n",
                id,
                nickname,
                dateOfBirth.toString());
    }
}
