package com.instinctools.egor.mentoring.spring.dal.entities;

import com.instinctools.egor.mentoring.spring.core.entity.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "User")
public class UserEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "name")
    private String userName;

    @Column(name = "birth_date")
    private Date birthDate;

    public UserEntity() {
    }

    private UserEntity(final String userName, final Date birthDate) {
        this.userName = userName;
        this.birthDate = birthDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public Date getBirthDate() {
        return new Date(birthDate.getTime());
    }

    public void setBirthDate(final Date birthDate) {
        this.birthDate = new Date(birthDate.getTime());
    }

    public User toUser() {
        User user = new User(userName, birthDate);
        user.setId(id);
        return user;
    }

    public static UserEntity fromUser(final User user) {
        UserEntity userEntity = new UserEntity(user.getUserName(), user.getDateOfBirth());
        userEntity.id = user.getId();
        return userEntity;
    }

    @Override
    public String toString() {
        return String.format("User:%nid: %s %nname: %s %nBirth date: %s", id, userName, birthDate);
    }
}
