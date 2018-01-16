package com.instinctools.egor.mentoring.Web.core.factory;

import com.instinctools.egor.mentoring.Web.core.entity.Book;
import com.instinctools.egor.mentoring.Web.core.entity.User;

import java.util.Date;

public interface EntityFactory {
    User createUser(String name, Date date);
    Book createBook(String name, String author);
}
