package com.instinctools.egor.mentoring.noSQL.factory;

import com.instinctools.egor.mentoring.noSQL.entity.Book;
import com.instinctools.egor.mentoring.noSQL.entity.User;

import java.util.Date;

public interface EntityFactory {
    User createUser(String name, Date date);
    Book createBook(String name, String author);
}
