package com.instinctools.egor.mentoring.web.core.entity;

import java.util.UUID;

public class Book {
    private String id;
    private String author;
    private String name;
    private User owner;

    public Book(final String author, final String name) {
        id = UUID.randomUUID().toString();
        this.author = author;
        this.name = name;
    }

    public User getOwner() {
        return this.owner;
    }

    public void setOwner(final User user) {
        this.owner = user;
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

    @Override
    public String toString() {
        return String.format("id: %s%nname: %s%nauthor: %s%nowner: %s", id, name, author, owner);
    }
}
