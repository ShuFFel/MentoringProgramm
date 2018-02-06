package com.instinctools.egor.mentoring.spring.dal.entities;

import com.instinctools.egor.mentoring.spring.core.entity.Book;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "Book")
public class BookEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity owner;

    public BookEntity() {
    }

    public BookEntity(final String name, final String author) {
        this.name = name;
        this.author = author;
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

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(final UserEntity owner) {
        this.owner = owner;
    }

    public Book toBook() {
        Book book = new Book(name, author);
        book.setId(id);
        book.setOwner((owner != null) ? owner.toUser() : null);
        return book;
    }

    public static BookEntity fromBook(final Book book) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.id = book.getId();
        bookEntity.name = book.getName();
        bookEntity.author = book.getAuthor();
        bookEntity.owner = (book.getOwner() != null) ? UserEntity.fromUser(book.getOwner()) : null;
        return bookEntity;
    }

    @Override
    public String toString() {
        return String.format("Book:%nid: %s %nname: %s %nauthor: %s %nowner: %s", id, name, author, owner);
    }
}
