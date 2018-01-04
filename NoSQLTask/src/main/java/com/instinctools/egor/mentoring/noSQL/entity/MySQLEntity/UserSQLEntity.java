package com.instinctools.egor.mentoring.noSQL.entity.MySQLEntity;

import com.instinctools.egor.mentoring.noSQL.entity.Book;
import com.instinctools.egor.mentoring.noSQL.entity.MySQLEntity.BookSQLEntity;
import com.instinctools.egor.mentoring.noSQL.entity.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity(name = "User")
public class UserSQLEntity implements User {

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

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public void addBook(Book book){
        book.setOwner(this);
    }

    public void removeBook(Book book){
        book.setOwner(null);
    }

    @Override
    public String toString() {
        return String.format("name: %s \ndate_of_birth: %s \n", user_name, date_of_birth);
    }
}
