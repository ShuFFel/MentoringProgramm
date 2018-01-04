package com.instinctools.egor.mentoring.noSQL.DAO.DAOUtil;

import com.mongodb.DB;
import com.mongodb.MongoClient;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.net.UnknownHostException;

public class DAOUtil {
    private static MongoClient mongoClient;
    private static EntityManagerFactory factory;

    private DAOUtil(){}

    public static EntityManagerFactory getSessionFactory(){
        if(factory == null){
            factory = Persistence.createEntityManagerFactory("my-perst");
        }
        return factory;
    }

    public static DB getMongoDB(){
        if(mongoClient == null){
            try {
                mongoClient = new MongoClient("localhost",27017);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        return mongoClient.getDB("test");
    }

}
