package com.instinctools.egor.mentoring.spring.web.dto;

import com.instinctools.egor.mentoring.spring.core.entity.Book;

public class BookDTO {
    private String name;

    private String author;

    public BookDTO() {
    }

    public BookDTO(final String name, final String author) {
        this.name = name;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Book toModel() {
        Book book = new Book(name, author);
        return book;
    }
}

