package com.instinctools.egor.mentoring.web.core.entity;

import java.util.UUID;

public class Book {
    private String id;
    private String author;
    private String name;
    private User owner;

    public Book() {
        id = UUID.randomUUID().toString();
    }

    public Book(String author, String name) {
        id = UUID.randomUUID().toString();
        this.author = author;
        this.name = name;
    }

    public User getOwner() {
        return this.owner;
    }

    public void setOwner(User user) {
        this.owner = user;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
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
        return String.format("id: %s \nname: %s\nauthor: %s\nowner: %s", id, name, author, owner);
    }
}
