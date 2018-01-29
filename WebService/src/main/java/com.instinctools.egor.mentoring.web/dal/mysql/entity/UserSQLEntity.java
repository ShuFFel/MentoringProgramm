package com.instinctools.egor.mentoring.web.dal.mysql.entity;

import com.instinctools.egor.mentoring.web.core.entity.Book;
import com.instinctools.egor.mentoring.web.core.entity.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity(name = "Users")
public class UserSQLEntity {

    @Id
    private String id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    public UserSQLEntity(final String userName, final Date dateOfBirth) {
        this.id = UUID.randomUUID().toString();
        this.userName = userName;
        this.dateOfBirth = new Date(dateOfBirth.getTime());
    }

    public UserSQLEntity() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public Date getDateOfBirth() {
        return new Date(dateOfBirth.getTime());
    }

    public void setDateOfBirth(final Date dateOfBirth) {
        this.dateOfBirth = new Date(dateOfBirth.getTime());
    }

    public void addBook(final Book book) {
        book.setOwner(this.toUser());
    }

    public void removeBook(final Book book) {
        book.setOwner(null);
    }

    public User toUser() {
        User user = new User(userName, dateOfBirth);
        user.setId(id);
        return user;
    }

    public static UserSQLEntity fromUser(final User user) {
        UserSQLEntity sqlEntity = new UserSQLEntity();
        sqlEntity.setId(user.getId());
        sqlEntity.setDateOfBirth(user.getDateOfBirth());
        sqlEntity.setUserName(user.getUserName());
        return sqlEntity;
    }
}
