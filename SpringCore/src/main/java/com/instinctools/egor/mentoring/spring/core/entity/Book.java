package com.instinctools.egor.mentoring.spring.core.entity;

import java.util.UUID;

public class Book {
    private String id;
    private String author;
    private String name;
    private User owner;

    public Book() {
        id = UUID.randomUUID().toString();
    }

    public Book(final String name, final String author) {
        this.author = author;
        this.name = name;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getName() {
        return this.name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(final User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return String.format("id: %s %nname: %s%nauthor: %s", id, name, author);
    }
}
