package com.instinctools.egor.mentoring.web.core.factory;

import com.instinctools.egor.mentoring.web.core.entity.Book;
import com.instinctools.egor.mentoring.web.core.entity.User;

import java.util.Date;

public interface EntityFactory {
    User createUser(String name, Date date);
    Book createBook(String name, String author);
}
