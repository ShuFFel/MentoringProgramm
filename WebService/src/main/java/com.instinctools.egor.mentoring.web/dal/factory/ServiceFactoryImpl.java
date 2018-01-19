package com.instinctools.egor.mentoring.web.dal.factory;

import com.instinctools.egor.mentoring.web.core.factory.ServiceFactory;
import com.instinctools.egor.mentoring.web.core.repository.BookRepository;
import com.instinctools.egor.mentoring.web.core.repository.UserRepository;
import com.instinctools.egor.mentoring.web.dal.mongo.dao.MongoBookDAO;
import com.instinctools.egor.mentoring.web.dal.mongo.dao.MongoUserDAO;
import com.instinctools.egor.mentoring.web.dal.mysql.dao.MySQLBookDAO;
import com.instinctools.egor.mentoring.web.dal.mysql.dao.MySQLUserDAO;
import com.instinctools.egor.mentoring.web.factories.SettingService;
import com.instinctools.egor.mentoring.web.factories.StorageType;
import com.mongodb.DB;

import javax.persistence.EntityManager;

public class ServiceFactoryImpl implements ServiceFactory {
    private SettingService settingService;
    private EntityManager entityManager;
    private DB mongoDB;
    private MongoBookDAO mongoBookDAO;
    private MySQLBookDAO mySQLBookDAO;
    private MongoUserDAO mongoUserDAO;
    private MySQLUserDAO mySQLUserDAO;

    public ServiceFactoryImpl(SettingService settingService, EntityManager entityManager, DB mongoDB) {
        this.settingService = settingService;
        this.entityManager = entityManager;
        this.mongoDB = mongoDB;
    }

    @Override
    public UserRepository getUserRepo() {
        if (settingService.getCurrentType() == StorageType.MONGO) {
            if (mongoUserDAO == null) {
                mongoUserDAO = new MongoUserDAO(mongoDB);
            }
            return mongoUserDAO;
        } else {
            if (mySQLUserDAO == null) {
                mySQLUserDAO = new MySQLUserDAO(entityManager);
            }
            return mySQLUserDAO;
        }
    }

    @Override
    public BookRepository getBookRepo() {
        if (settingService.getCurrentType() == StorageType.MONGO) {
            if (mongoBookDAO == null) {
                mongoBookDAO = new MongoBookDAO(mongoDB);
            }
            return mongoBookDAO;
        } else {
            if (mySQLBookDAO == null) {
                mySQLBookDAO = new MySQLBookDAO(entityManager);
            }
            return mySQLBookDAO;
        }
    }
}
