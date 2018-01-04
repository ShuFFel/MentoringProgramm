package com.instinctools.egor.mentoring.noSQL.factory;

import com.instinctools.egor.mentoring.noSQL.entity.Book;
import com.instinctools.egor.mentoring.noSQL.entity.MySQLEntity.BookSQLEntity;
import com.instinctools.egor.mentoring.noSQL.entity.User;
import com.instinctools.egor.mentoring.noSQL.entity.MySQLEntity.UserSQLEntity;

import java.util.Date;

public class MySQLEntityFactory implements EntityFactory {
    @Override
    public User createUser(String name, Date date) {
        return new UserSQLEntity(name, date);
    }

    @Override
    public Book createBook(String name, String author) {
        return new BookSQLEntity(name, author);
    }
}
