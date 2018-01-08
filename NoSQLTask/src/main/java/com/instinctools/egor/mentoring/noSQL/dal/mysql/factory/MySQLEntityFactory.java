package com.instinctools.egor.mentoring.noSQL.dal.mysql.factory;

import com.instinctools.egor.mentoring.noSQL.core.entity.Book;
import com.instinctools.egor.mentoring.noSQL.core.factory.EntityFactory;
import com.instinctools.egor.mentoring.noSQL.dal.mysql.entity.BookSQLEntity;
import com.instinctools.egor.mentoring.noSQL.core.entity.User;
import com.instinctools.egor.mentoring.noSQL.dal.mysql.entity.UserSQLEntity;

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
