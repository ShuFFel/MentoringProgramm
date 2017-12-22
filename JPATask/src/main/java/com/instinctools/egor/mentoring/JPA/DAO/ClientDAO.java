package com.instinctools.egor.mentoring.JPA.DAO;

import com.instinctools.egor.mentoring.JPA.Entity.Client;

import java.sql.SQLException;
import java.util.List;

public interface ClientDAO {
    public void createClient(Client client) throws SQLException;
    public void deleteClient(Client client) throws SQLException;
    public List<Client> getAllClients() throws SQLException;
    public Client updateClient(Client client) throws SQLException;
    public Client getById(long id) throws SQLException;
}
