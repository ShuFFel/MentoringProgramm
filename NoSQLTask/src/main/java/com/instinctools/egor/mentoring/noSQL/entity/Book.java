package com.instinctools.egor.mentoring.noSQL.entity;

public interface Book {
    User getOwner();

    void setOwner(User user);

    void setAuthor(String author);

    void setName(String name);

    String get_id();

    String getAuthor();

    String getName();
}
