package com.instinctools.egor.mentoring.noSQL.entity.MySQLEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.instinctools.egor.mentoring.noSQL.entity.Book;
import com.instinctools.egor.mentoring.noSQL.entity.User;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "Book")
public class BookSQLEntity implements Book {

    @Id
    private String _id;

    @Column
    private String name;

    @Column
    private String author;

    @ManyToOne
    @JsonIgnore
    private UserSQLEntity owner;

    public BookSQLEntity(String name, String author) {
        this._id = UUID.randomUUID().toString();
        this.name = name;
        this.author = author;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        if(owner == null){
            this.owner = null;
            return;
        }
        UserSQLEntity user = new UserSQLEntity();
        user.set_id(owner.get_id());
        this.owner = user;
    }

    public BookSQLEntity() {
        this._id = UUID.randomUUID().toString();
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    @Override
    public String toString() {
        return String.format("id: %s \nname: %s\nauthor: %s\nowner: %s", _id, name, author, (owner != null)?owner.get_id():owner);
    }
}
