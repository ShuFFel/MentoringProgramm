package com.instinctools.egor.mentoring.JPA.DAO.DAOUtil;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAOUtil {
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("my-perst");

    public static EntityManagerFactory getSessionFactory(){
        return factory;
    }
}