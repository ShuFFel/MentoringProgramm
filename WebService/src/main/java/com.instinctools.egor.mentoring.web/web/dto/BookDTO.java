package com.instinctools.egor.mentoring.web.web.dto;

import com.instinctools.egor.mentoring.web.core.entity.Book;

public class BookDTO {
    private String name;
    private String author;


    public BookDTO() {
    }

    public BookDTO(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public Book toModel() {
        Book book = new Book(author, name);
        return book;
    }
}
