package com.instinctools.egor.mentoring.JPA.DAO.DAOFactory;

import com.instinctools.egor.mentoring.JPA.DAO.ClientDAO;
import com.instinctools.egor.mentoring.JPA.DAO.DAOImpl.ClientDAOImpl;
import com.instinctools.egor.mentoring.JPA.Entity.Client;

public class Factory {
    private static ClientDAO clientDAO = null;

    private static Factory instance = null;
    public static Factory getInstance(){
        if(instance == null){
            instance = new Factory();
        }
        return instance;
    }

    public static ClientDAO getClientDAO() {
        if(clientDAO == null){
            clientDAO = new ClientDAOImpl();
        }
        return clientDAO;
    }
}
