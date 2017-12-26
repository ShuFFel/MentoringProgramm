package com.instinctools.egor.mentoring.JPA.DAO.DAOImpl;

import com.instinctools.egor.mentoring.JPA.DAO.ClientDAO;
import com.instinctools.egor.mentoring.JPA.DAO.DAOUtil.DAOUtil;
import com.instinctools.egor.mentoring.JPA.entity.Client;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

public class ClientDAOImpl implements ClientDAO {

    private static ClientDAOImpl clientDAO = null;

    public static ClientDAOImpl getInstance(){
        if(clientDAO == null){
            clientDAO = new ClientDAOImpl();
        }
        return clientDAO;
    }
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

    public Client updateClient(Client client) throws SQLException {
        EntityManager entityManager = DAOUtil.getSessionFactory().createEntityManager();
        Client updatedClient = client;
        try{
            entityManager.getTransaction().begin();
            entityManager.merge(client);
            updatedClient = entityManager.find(Client.class, client.getUserId());
            entityManager.getTransaction().commit();
        }
        finally {
            entityManager.close();
        }
        return updatedClient;
    }

    public Client getById(long id) throws SQLException {
        EntityManager entityManager = DAOUtil.getSessionFactory().createEntityManager();
        Client clientToReturn = null;
        try {
            clientToReturn = entityManager.find(Client.class, id);
        }finally {
            entityManager.close();
        }
        return clientToReturn;
    }
}
