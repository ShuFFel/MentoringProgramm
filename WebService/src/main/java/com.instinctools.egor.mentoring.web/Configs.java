package com.instinctools.egor.mentoring.web;

import com.instinctools.egor.mentoring.web.factories.StorageType;
import com.mongodb.DB;
import com.mongodb.MongoClient;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.Properties;

public class Configs {
    private Properties configProperties;
    private StorageType storageType;
    private String userSOAPAddress;
    private String bookSOAPAddress;
    private DB mongoDB;

    private EntityManager entityManager;

    public Configs(final String propertyFileName) throws IOException {
        configProperties = new Properties();
        InputStream is;
        is = getClass().getClassLoader().getResourceAsStream(propertyFileName);
        configProperties.load(is);
    }

    public DB getMongoDB() throws UnknownHostException {
        if (mongoDB == null) {
            int mongoClientPort = Integer.parseInt(configProperties.getProperty("mongoClientPort"));
            String mongoClientHost = configProperties.getProperty("mongoClientHost");
            String mongoDBName = configProperties.getProperty("mongoDBName");
            mongoDB = new MongoClient(mongoClientHost, mongoClientPort).getDB(mongoDBName);
        }
        return mongoDB;
    }

    public StorageType getStorageType() {
        if (storageType == null) {
            storageType = StorageType.valueOf(configProperties.getProperty("storageType"));
        }
        return storageType;
    }

    public String getUserSOAPAddress() {
        if (userSOAPAddress == null) {
            userSOAPAddress = configProperties.getProperty("userEndpointAddress");
        }
        return userSOAPAddress;
    }

    public String getBookSOAPAddress() {
        if (bookSOAPAddress == null) {
            bookSOAPAddress = configProperties.getProperty("bookEndpointAddress");
        }
        return bookSOAPAddress;
    }

    public EntityManager getEntityManager() {
        if (entityManager == null) {
            String persistenceUnitName = configProperties.getProperty("persistenceUnitName");
            entityManager = Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();
        }
        return entityManager;
    }
}
