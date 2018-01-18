package com.instinctools.egor.mentoring.web.dal.mysql.entity;

import com.instinctools.egor.mentoring.web.core.entity.Book;
import com.instinctools.egor.mentoring.web.core.entity.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity(name = "Users")
public class UserSQLEntity{

    @Id
    private String _id;

    @Column
    private String user_name;

    @Column
    private Date date_of_birth;

    public UserSQLEntity(String user_name, Date date_of_birth) {
        this._id = UUID.randomUUID().toString();
        this.user_name = user_name;
        this.date_of_birth = date_of_birth;
    }

    public UserSQLEntity() {
        this._id = UUID.randomUUID().toString();
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String id) {
        this._id = id;
    }

    public String getUserName() {
        return user_name;
    }

    public void setUserName(String user_name) {
        this.user_name = user_name;
    }

    public Date getDateOfBirth() {
        return date_of_birth;
    }

    public void setDateOfBirth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public void addBook(Book book){
        book.setOwner(this.toUser());
    }

    public void removeBook(Book book){
        book.setOwner(null);
    }

    public User toUser(){
        User user = new User();
        user.setId(_id);
        user.setUserName(user_name);
        user.setDateOfBirth(date_of_birth);
        return user;
    }

    public static UserSQLEntity fromUser(User user){
        UserSQLEntity sqlEntity = new UserSQLEntity();
        sqlEntity.set_id(user.getId());
        sqlEntity.setDateOfBirth(user.getDateOfBirth());
        sqlEntity.setUserName(user.getUserName());
        return sqlEntity;
    }
}
