package com.instinctools.egor.mentoring.JPA.testing;

import com.instinctools.egor.mentoring.JPA.commandlinemenu.AccountWorkingMenu;
import com.instinctools.egor.mentoring.JPA.entity.Account;
import com.instinctools.egor.mentoring.JPA.entity.Client;
import com.instinctools.egor.mentoring.JPA.service.ClientService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountMenuTest {

    Client client = Mockito.mock(Client.class);
    ClientService service = Mockito.mock(ClientService.class);
    Account account = Mockito.mock(Account.class);

    @Test
    public void createAccountTest() {
        Mockito.when(client.toString()).thenReturn("aaaa");
        try {
            Mockito.when(service.createAccount(Mockito.anyLong(), (Account) Mockito.any())).thenReturn(client);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (ByteArrayOutputStream outputStream = TestUtil.prepare("1\n" +
                "0\n" +
                "00-00-00\n" +
                "0\n" +
                "0\n") ) {
            AccountWorkingMenu menu = new AccountWorkingMenu(service);
            menu.start(client);
            Assert.assertTrue(outputStream.toString().contains("Input nickname: "));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteAccountTest() {
        Mockito.when(client.toString()).thenReturn("aaaa");
        Mockito.when(account.toString()).thenReturn("aaaa");
        List<Account> accounts = new ArrayList<>();
        accounts.add(account);
        Mockito.when(client.getAccounts()).thenReturn(accounts);
        try {
            Mockito.when(service.deleteAccount((Client) Mockito.any(), (Account) Mockito.any())).thenReturn(client);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (ByteArrayOutputStream outputStream = TestUtil.prepare("3\n" +
                "1\n" +
                "0\n") ) {
            AccountWorkingMenu menu = new AccountWorkingMenu(service);
            menu.start(client);
            Assert.assertTrue(outputStream.toString().contains("Deleted Account:"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateAccountTest() {
        Mockito.when(client.toString()).thenReturn("aaaa");
        Mockito.when(account.toString()).thenReturn("aaaa");
        List<Account> accounts = new ArrayList<>();
        accounts.add(account);
        Mockito.when(client.getAccounts()).thenReturn(accounts);
        try {
            Mockito.when(service.updateClient((Client) Mockito.any())).thenReturn(client);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (ByteArrayOutputStream outputStream = TestUtil.prepare("2\n" +
                     "1\n" +
                     "1\n" +
                     "00-00-00\n" +
                     "0\n")) {
            AccountWorkingMenu menu = new AccountWorkingMenu(service);
            menu.start(client);
            Assert.assertTrue(outputStream.toString().contains("Input new nickname:"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
