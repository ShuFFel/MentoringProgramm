package com.instinctools.egor.mentoring.Web.dal.mongo.factory;

import com.instinctools.egor.mentoring.Web.core.entity.Book;
import com.instinctools.egor.mentoring.Web.core.entity.User;
import com.instinctools.egor.mentoring.Web.core.factory.EntityFactory;
import com.instinctools.egor.mentoring.Web.dal.mongo.entity.BookMongoEntity;
import com.instinctools.egor.mentoring.Web.dal.mongo.entity.UserMongoEntity;

import java.util.Date;

public class MongoEntityFactory implements EntityFactory {
    @Override
    public User createUser(String name, Date date) {
        return new UserMongoEntity(name, date).toUser();
    }

    @Override
    public Book createBook(String name, String author) {
        return new BookMongoEntity(name, author).toBook();
    }
}
