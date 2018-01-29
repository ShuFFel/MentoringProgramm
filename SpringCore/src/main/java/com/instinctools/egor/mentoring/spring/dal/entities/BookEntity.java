package com.instinctools.egor.mentoring.spring.dal.entities;

import com.instinctools.egor.mentoring.spring.core.entity.Book;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

    public Book toBook() {
        Book book = new Book(author, name);
        book.setId(id);
        return book;
    }

    public static BookEntity fromBook(final Book book) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.id = book.getId();
        bookEntity.name = book.getName();
        bookEntity.author = book.getAuthor();
        return bookEntity;
    }

    @Override
    public String toString() {
        return String.format("Book:%nid: %s %nname: %s %nauthor: %s", id, name, author);
    }
}
