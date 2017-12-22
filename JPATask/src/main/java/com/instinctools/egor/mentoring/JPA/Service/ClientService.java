package com.instinctools.egor.mentoring.JPA.Service;

import com.instinctools.egor.mentoring.JPA.Entity.Client;

import java.sql.SQLException;
import java.util.List;

public interface ClientService {
    public List<Client> getAllClients();
    public void createClient(Client client) throws SQLException;
    public void deleteClient(Client client) throws SQLException;
}
