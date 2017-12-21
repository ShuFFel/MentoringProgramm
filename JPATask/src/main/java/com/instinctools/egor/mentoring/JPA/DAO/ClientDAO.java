package com.instinctools.egor.mentoring.JPA.DAO;

import com.instinctools.egor.mentoring.JPA.Entity.Account;
import com.instinctools.egor.mentoring.JPA.Entity.Client;

import java.sql.SQLException;
import java.util.List;

public interface ClientDAO {
    public void createClient(Client client) throws SQLException;
    public void deleteClient(Client client) throws SQLException;
    public List<Client> getAllClients() throws SQLException;
    public void createAccount(Client client, Account account) throws SQLException;
    public void deleteAccount(Client client, Account account) throws SQLException;
    public void changeAccount(Account account) throws SQLException;

}
