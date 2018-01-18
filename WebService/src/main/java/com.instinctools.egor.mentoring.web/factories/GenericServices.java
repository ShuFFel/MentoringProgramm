package com.instinctools.egor.mentoring.web.factories;

import com.instinctools.egor.mentoring.web.core.factory.EntityFactory;
import com.instinctools.egor.mentoring.web.core.repository.BookRepository;
import com.instinctools.egor.mentoring.web.core.repository.UserRepository;
import com.instinctools.egor.mentoring.web.core.services.BookService;
import com.instinctools.egor.mentoring.web.core.services.UserService;
import com.instinctools.egor.mentoring.web.core.services.serviceimpl.BookServiceImpl;
import com.instinctools.egor.mentoring.web.core.services.serviceimpl.UserServiceImpl;
import com.instinctools.egor.mentoring.web.dal.mongo.dao.MongoBookDAO;
import com.instinctools.egor.mentoring.web.dal.mongo.dao.MongoUserDAO;
import com.instinctools.egor.mentoring.web.dal.mongo.factory.MongoEntityFactory;
import com.instinctools.egor.mentoring.web.dal.mysql.dao.MySQLBookDAO;
import com.instinctools.egor.mentoring.web.dal.mysql.dao.MySQLUserDAO;
import com.instinctools.egor.mentoring.web.dal.mysql.factory.MySQLEntityFactory;
import com.mongodb.DB;
import com.mongodb.MongoClient;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.net.UnknownHostException;

public class GenericServices {
    private static UserService mongoUserService;
    private static UserService mysqlUserService;
    private static BookService mongoBookService;
    private static BookService mysqlBookService;
    private static EntityFactory factory;

    public GenericServices() {
    }

    public static UserService getUserService(StorageType type) {
        if (type == StorageType.MONGO)
            return mongoUserService;
        else
            return mysqlUserService;
    }

    public static BookService getBookService(StorageType type) {
        if (type == StorageType.MONGO)
            return mongoBookService;
        else
            return mysqlBookService;
    }

    public static EntityFactory getFactory() {
        return factory;
    }

    public GenericServices invoke() {
        UserRepository userRepository;
        BookRepository bookRepository;
        try {
            DB mongoDB = new MongoClient("localhost", 27017).getDB("test");
            userRepository = new MongoUserDAO(mongoDB);
            bookRepository = new MongoBookDAO(mongoDB);
            mongoUserService = new UserServiceImpl(userRepository, bookRepository);
            mongoBookService = new BookServiceImpl(userRepository, bookRepository);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        factory = new MongoEntityFactory();
        EntityManager manager = Persistence.createEntityManagerFactory("my-perst").createEntityManager();
        userRepository = new MySQLUserDAO(manager);
        bookRepository = new MySQLBookDAO(manager);
        factory = new MySQLEntityFactory();
        mysqlUserService = new UserServiceImpl(userRepository, bookRepository);
        mysqlBookService = new BookServiceImpl(userRepository, bookRepository);
        return this;
    }
}
