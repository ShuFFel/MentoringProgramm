package com.instinctools.egor.mentoring.JPA.testing;

import com.instinctools.egor.mentoring.JPA.commandlinemenu.AccountWorkingMenu;
import com.instinctools.egor.mentoring.JPA.commandlinemenu.ClientWorkingMenu;
import com.instinctools.egor.mentoring.JPA.entity.Client;
import com.instinctools.egor.mentoring.JPA.service.ClientService;
import com.instinctools.egor.mentoring.JPA.service.DAOServiceImpl.ClientServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientMenuTest {

    ClientService service = Mockito.mock(ClientService.class);
    Client client = Mockito.mock(Client.class);
    AccountWorkingMenu accountWorkingMenu = Mockito.mock(AccountWorkingMenu.class);

    @Test
    public void createClientMenuTest() {
        try (ByteArrayOutputStream outputStream = TestUtil.prepare("1\n" +
                "0\n" +
                "0\n" +
                "0\n") ){

            ClientWorkingMenu menu = new ClientWorkingMenu(service, accountWorkingMenu);
            menu.start();
            Assert.assertTrue(outputStream.toString().contains("input username: "));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteClientMenuTest() {
        List<Client> clients = new ArrayList<>();
        clients.add(client);
        Mockito.when(service.getAllClients()).thenReturn(clients);
        Mockito.when(client.toString()).thenReturn("aaa");
        try(ByteArrayOutputStream outputStream = TestUtil.prepare("4\n" +
                "1\n" +
                "0\n")) {
            ClientWorkingMenu menu = new ClientWorkingMenu(service, accountWorkingMenu);
            menu.start();
            Assert.assertTrue(outputStream.toString().contains("Deleted!"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void showClientsTest() {
        try(ByteArrayOutputStream outputStream = TestUtil.prepare("3\n" +
                "0\n")) {
            ClientWorkingMenu menu = new ClientWorkingMenu(service, accountWorkingMenu);
            menu.start();
            Assert.assertTrue(outputStream.toString().contains("List of all:"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void exitCommandTest() {
        try(ByteArrayOutputStream outputStream = TestUtil.prepare("0\n")) {
            ClientWorkingMenu menu = new ClientWorkingMenu(service, accountWorkingMenu);
            menu.start();
            Assert.assertTrue(outputStream.toString().contains("EXITED"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void workingWithClientTest(){
        List<Client> clients = new ArrayList<>();
        clients.add(client);
        Mockito.when(service.getAllClients()).thenReturn(clients);
        Mockito.when(client.toString()).thenReturn("aaa");
        try(ByteArrayOutputStream outputStream = TestUtil.prepare("2\n" +
                "1\n" +
                "0\n" +
                "0\n")) {
            ClientWorkingMenu menu = new ClientWorkingMenu(service, accountWorkingMenu);
            menu.start();
            Assert.assertTrue(outputStream.toString().contains("Your client:"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
