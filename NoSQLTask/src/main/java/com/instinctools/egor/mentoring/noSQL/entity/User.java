package com.instinctools.egor.mentoring.noSQL.entity;


import java.util.Date;
import java.util.List;

public interface User {
    void addBook(Book book);

    void removeBook(Book book);

    String get_id();

    Date getDate_of_birth();

    String getUser_name();

    void setUser_name(String user_name);

    void setDate_of_birth(Date date_of_birth);
}
