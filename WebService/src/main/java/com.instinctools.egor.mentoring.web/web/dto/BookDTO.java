package com.instinctools.egor.mentoring.web.web.dto;

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
}
