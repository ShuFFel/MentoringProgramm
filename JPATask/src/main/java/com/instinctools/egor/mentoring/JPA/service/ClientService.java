package com.instinctools.egor.mentoring.JPA.service;

import com.instinctools.egor.mentoring.JPA.entity.Account;
import com.instinctools.egor.mentoring.JPA.entity.Client;

import java.sql.SQLException;
import java.util.List;

public interface ClientService {
    public List<Client> getAllClients();
    public void createClient(Client client) throws SQLException;
    public void deleteClient(Client client) throws SQLException;
    public Client createAccount(long id, Account account) throws SQLException;
    public Client deleteAccount(Client client, Account account) throws SQLException;
    public Client updateClient(Client client) throws SQLException;
}
