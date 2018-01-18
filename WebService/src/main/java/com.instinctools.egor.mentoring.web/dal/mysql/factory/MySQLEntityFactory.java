package com.instinctools.egor.mentoring.web.dal.mysql.factory;

import com.instinctools.egor.mentoring.web.core.entity.Book;
import com.instinctools.egor.mentoring.web.core.entity.User;
import com.instinctools.egor.mentoring.web.core.factory.EntityFactory;
import com.instinctools.egor.mentoring.web.dal.mysql.entity.BookSQLEntity;
import com.instinctools.egor.mentoring.web.dal.mysql.entity.UserSQLEntity;

import java.util.Date;

public class MySQLEntityFactory implements EntityFactory {
    @Override
    public User createUser(String name, Date date) {
        return new UserSQLEntity(name, date).toUser();
    }

    @Override
    public Book createBook(String name, String author) {
        return new BookSQLEntity(name, author).toBook();
    }
}
