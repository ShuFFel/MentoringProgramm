package com.instinctools.egor.mentoring.JPA.service.DAOServiceImpl;

import com.instinctools.egor.mentoring.JPA.DAO.ClientDAO;
import com.instinctools.egor.mentoring.JPA.DAO.DAOImpl.ClientDAOImpl;
import com.instinctools.egor.mentoring.JPA.entity.Account;
import com.instinctools.egor.mentoring.JPA.entity.Client;
import com.instinctools.egor.mentoring.JPA.service.ClientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class ClientServiceImpl implements ClientService {

    private ClientDAO clientDAO;

    private static final Logger logger = LogManager.getLogger(ClientServiceImpl.class);

    public ClientServiceImpl() {
        this.clientDAO = ClientDAOImpl.getInstance();
    }

    public List<Client> getAllClients() {
        List<Client> list = null;
        try {
            list =  clientDAO.getAllClients();
        } catch (SQLException e) {
            log("Fail to load ClientList\n");
        }
        return list;
    }

    public void createClient(Client client) throws SQLException {
        this.clientDAO.createClient(client);
    }

    public void deleteClient(Client client) throws SQLException {
        this.clientDAO.deleteClient(client);
    }

    public Client createAccount(long id, Account account) throws SQLException {
        Client clientToWork = clientDAO.getById(id);
        clientToWork.addAccount(account);
        return clientDAO.updateClient(clientToWork);
    }

    public Client deleteAccount(Client client, Account account) throws SQLException {
        client.removeAccount(account);
        return clientDAO.updateClient(client);

    }


    public Client updateClient(Client client) throws SQLException {
        clientDAO.updateClient(client);
        return clientDAO.getById(client.getUserId());
    }

    private static void log(String s){
        logger.info(s);
    }
}
