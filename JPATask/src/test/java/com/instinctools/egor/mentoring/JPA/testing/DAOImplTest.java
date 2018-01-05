package com.instinctools.egor.mentoring.JPA.testing;

import com.instinctools.egor.mentoring.JPA.DAO.ClientDAO;
import com.instinctools.egor.mentoring.JPA.DAO.DAOImpl.ClientDAOImpl;
import com.instinctools.egor.mentoring.JPA.entity.Client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class DAOImplTest {

    ClientDAO clientDAO = new ClientDAOImpl();

    @Test
    public void readAllClientsTest(){
        List clients = null;
        try {
            clients = clientDAO.getAllClients();
        } catch (SQLException e) {
            Assert.fail();
            e.printStackTrace();
        }
        Assert.assertNotNull(clients);
    }

    @Test
    public void writeAndGetBuIdTest(){
        Client client = new Client();
        try {
            clientDAO.createClient(client);
            client = clientDAO.getById(client.getUserId());
            Assert.assertNotEquals(0, client.getUserId());
            clientDAO.deleteClient(client);
        } catch (SQLException e) {
            Assert.fail();
            e.printStackTrace();
        }
    }

    @Test
    public void deleteTest(){
        Client client = new Client();
        try {
            clientDAO.createClient(client);
            Assert.assertNotNull(clientDAO.getById(client.getUserId()));
            clientDAO.deleteClient(client);
            Assert.assertNull(clientDAO.getById(client.getUserId()));
        } catch (SQLException e) {
            Assert.fail();
            e.printStackTrace();
        }
    }

    @Test
    public void updateTest(){
        String firsLogin = "aaa";
        String secondLogin = "bbb";
        Client client = new Client(firsLogin, "");
        try{
            clientDAO.createClient(client);
            Assert.assertNotEquals(0, client.getUserId());
            client.setUserName(secondLogin);
            clientDAO.updateClient(client);
            Client client2 = clientDAO.getById(client.getUserId());
            Assert.assertEquals(secondLogin, client2.getUserName());
            clientDAO.deleteClient(client);
        } catch (SQLException e) {
            Assert.fail();
            e.printStackTrace();
        }


    }

}
