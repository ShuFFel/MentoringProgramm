package com.instinctools.egor.mentoring.JPA.DAO.DAOImpl;

import com.instinctools.egor.mentoring.JPA.DAO.ClientDAO;
import com.instinctools.egor.mentoring.JPA.DAO.DAOUtil.DAOUtil;
import com.instinctools.egor.mentoring.JPA.Entity.Account;
import com.instinctools.egor.mentoring.JPA.Entity.Client;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

public class ClientDAOImpl implements ClientDAO {
    public void createClient(Client client) throws SQLException {
        EntityManager session = DAOUtil.getSessionFactory().createEntityManager();
        try {
            session.getTransaction().begin();
            session.persist(client);
            session.getTransaction().commit();
        }
        finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public void deleteClient(Client client) throws SQLException {
        EntityManager session = DAOUtil.getSessionFactory().createEntityManager();
        try {
            session.getTransaction().begin();
            session.remove(session.contains(client) ? client : session.merge(client));
            session.flush();
            session.getTransaction().commit();
        }
        finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public List<Client> getAllClients() throws SQLException {
        EntityManager session = DAOUtil.getSessionFactory().createEntityManager();
        List<Client> clients = null;
        try {
            session.getTransaction().begin();
            clients = session.createQuery("from Client", Client.class).getResultList();
            session.getTransaction().commit();
        }
        finally {
            session.close();
        }
        return clients;
    }

    public void createAccount(Client client, Account account) throws SQLException {
        EntityManager entityManager = DAOUtil.getSessionFactory().createEntityManager();
        try {
           entityManager.getTransaction().begin();
           client.addAccount(account);
           entityManager.merge(client);
           entityManager.flush();
           entityManager.getTransaction().commit();
        }
        finally {
            entityManager.close();
        }
    }

    public void deleteAccount(Client client, Account account) throws SQLException {
        EntityManager entityManager = DAOUtil.getSessionFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            client.removeAccount(account);
            entityManager.merge(client);
            entityManager.getTransaction().commit();
        }
        finally {
            entityManager.close();
        }
    }

    public void changeAccount(Account account) throws SQLException {
        EntityManager entityManager = DAOUtil.getSessionFactory().createEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.merge(account);
            entityManager.flush();
            entityManager.getTransaction().commit();
        }finally {
            entityManager.close();
        }

    }
}
