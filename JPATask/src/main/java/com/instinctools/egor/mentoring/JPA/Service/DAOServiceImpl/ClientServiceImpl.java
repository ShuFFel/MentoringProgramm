package com.instinctools.egor.mentoring.JPA.Service.DAOServiceImpl;

import com.instinctools.egor.mentoring.JPA.DAO.ClientDAO;
import com.instinctools.egor.mentoring.JPA.DAO.DAOFactory.Factory;
import com.instinctools.egor.mentoring.JPA.Entity.Client;
import com.instinctools.egor.mentoring.JPA.Service.ClientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class ClientServiceImpl implements ClientService {
    ClientDAO client;

    private static final Logger logger = LogManager.getLogger(ClientServiceImpl.class);

    public ClientServiceImpl() {
        this.client = Factory.getInstance().getClientDAO();
    }

    public List<Client> getAllClients() {
        List<Client> list = null;
        try {
            list =  client.getAllClients();
        } catch (SQLException e) {
            log("Fail to load ClientList\n");
        }
        return list;
    }

    public void createClient(Client client) throws SQLException {
        this.client.createClient(client);
    }

    public void deleteClient(Client client) throws SQLException {
        this.client.deleteClient(client);
    }

    private static void log(String s){
        logger.info(s);
    }
}
