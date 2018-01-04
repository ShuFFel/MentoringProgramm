package com.instinctools.egor.mentoring.noSQL.factory;

import com.instinctools.egor.mentoring.noSQL.entity.Book;
import com.instinctools.egor.mentoring.noSQL.entity.MongoEntity.BookMongoEntity;
import com.instinctools.egor.mentoring.noSQL.entity.MongoEntity.UserMongoEntity;
import com.instinctools.egor.mentoring.noSQL.entity.User;

import java.util.Date;

public class MongoEntityFactory implements EntityFactory {
    @Override
    public User createUser(String name, Date date) {
        return new UserMongoEntity(name, date);
    }

    @Override
    public Book createBook(String name, String author) {
        return new BookMongoEntity(name, author);
    }
}
