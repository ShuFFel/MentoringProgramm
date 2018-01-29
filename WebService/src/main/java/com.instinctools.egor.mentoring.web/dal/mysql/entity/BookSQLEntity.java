package com.instinctools.egor.mentoring.web.dal.mysql.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.instinctools.egor.mentoring.web.core.entity.Book;
import com.instinctools.egor.mentoring.web.core.entity.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity(name = "Books")
public class BookSQLEntity {

    @Id
    private String id;

    @Column
    private String name;

    @Column
    private String author;

    @ManyToOne
    @JsonIgnore
    private UserSQLEntity owner;

    public BookSQLEntity(final String name, final String author) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.author = author;
    }

    public User getOwner() {
        return owner.toUser();
    }

    public void setOwner(final User owner) {
        if (owner == null) {
            this.owner = null;
            return;
        }
        UserSQLEntity user = new UserSQLEntity();
        user.setId(owner.getId());
        this.owner = user;
    }

    public BookSQLEntity() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }


    public Book toBook() {
        Book book = new Book(author, name);
        book.setId(id);
        book.setOwner((owner != null) ?
                        owner.toUser() :
                        null);
        return book;
    }

    public static BookSQLEntity fromBook(final Book book) {
        BookSQLEntity bookSQLEntity = new BookSQLEntity();
        bookSQLEntity.id = book.getId();
        bookSQLEntity.author = book.getAuthor();
        bookSQLEntity.name = book.getName();
        bookSQLEntity.owner = (book.getOwner() != null) ?
                        UserSQLEntity.fromUser(book.getOwner()) :
                        null;
        return bookSQLEntity;
    }

    @Override
    public String toString() {
        return String.format("id: %s %nname: %s%nauthor: %s%nowner: %s", id, name, author, owner);
    }
}
