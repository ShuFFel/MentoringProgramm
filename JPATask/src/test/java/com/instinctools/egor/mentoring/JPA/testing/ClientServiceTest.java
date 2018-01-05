package com.instinctools.egor.mentoring.JPA.testing;

import com.instinctools.egor.mentoring.JPA.DAO.ClientDAO;
import com.instinctools.egor.mentoring.JPA.entity.Account;
import com.instinctools.egor.mentoring.JPA.entity.Client;
import com.instinctools.egor.mentoring.JPA.service.ClientService;
import com.instinctools.egor.mentoring.JPA.service.DAOServiceImpl.ClientServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.SQLException;

public class ClientServiceTest {

    ClientDAO clientDAO = Mockito.mock(ClientDAO.class);
    ClientService service = new ClientServiceImpl(clientDAO);

    @Test
    public void createAccountServiceTest() throws SQLException {
        Client client = new Client();
        Client spy = Mockito.spy(client);

        Mockito.when(clientDAO.updateClient(Mockito.any())).thenReturn(spy);
        Mockito.when(clientDAO.getById(Mockito.anyLong())).thenReturn(spy);

        Assert.assertEquals(0, (long)spy.getAccounts().size());
        service.createAccount(spy.getUserId(), new Account());
        Assert.assertNotEquals(0,spy.getAccounts().size());
    }

    @Test
    public void deleteAccountServiceTest() throws SQLException {
        Client client = new Client();
        Client spy = Mockito.spy(client);

        Mockito.when(clientDAO.updateClient(Mockito.any())).thenReturn(spy);

        Account account = new Account();
        spy.addAccount(account);
        Assert.assertNotEquals(0, spy.getAccounts().size());
        service.deleteAccount(spy, account);
        Assert.assertEquals(0, spy.getAccounts().size());
    }

    @Test
    public void updateClientServiceTest() throws SQLException {
        Client client = new Client();
        Client spy = Mockito.spy(client);

        Mockito.when(clientDAO.updateClient(Mockito.any())).thenReturn(spy);
        Mockito.when(clientDAO.getById(Mockito.anyLong())).thenReturn(spy);

        Assert.assertEquals(spy, service.updateClient(spy));
    }

    @Test
    public void createClientService() throws SQLException {
        Client client = new Client();
        Client spy = Mockito.spy(client);

        service.createClient(spy);

        Mockito.verify(clientDAO, Mockito.times(1)).createClient(Mockito.any());
    }

    @Test
    public void deleteClientServiceTest() throws SQLException {
        Client client = new Client();
        Client spy = Mockito.spy(client);

        service.deleteClient(spy);

        Mockito.verify(clientDAO, Mockito.times(1)).deleteClient(Mockito.any());
    }

    @Test
    public void getAllClientsServiceTest() throws SQLException {
        Client client = new Client();
        Client spy = Mockito.spy(client);

        service.getAllClients();

        Mockito.verify(clientDAO, Mockito.times(1)).getAllClients();
    }

}
