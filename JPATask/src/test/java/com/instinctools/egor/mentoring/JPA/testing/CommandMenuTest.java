package com.instinctools.egor.mentoring.JPA.testing;

import com.instinctools.egor.mentoring.JPA.commandlinemenu.ClientWorkingMenu;
import com.instinctools.egor.mentoring.JPA.service.ClientService;
import com.instinctools.egor.mentoring.JPA.service.DAOServiceImpl.ClientServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CommandMenuTest {

    ClientService service = Mockito.mock(ClientService.class);

    @Test
    public void createClientMenuTest(){
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(("1\n" +
                    "0\n").getBytes());
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream out = new PrintStream(outputStream);
            System.setOut(out);
            System.setIn(in);

            ClientWorkingMenu menu = new ClientWorkingMenu(service);
            menu.start();
            Assert.assertTrue(outputStream.toString().contains("Chose client: "));
        }
        finally {
            System.setOut(System.out);
            System.setIn(System.in);
        }
    }

    @Test
    public void deleteClientMenuTest(){
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(("4\n" +
                    "0\n").getBytes());
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream out = new PrintStream(outputStream);
            System.setOut(out);
            System.setIn(in);

            ClientWorkingMenu menu = new ClientWorkingMenu(service);
            menu.start();
            Assert.assertTrue(outputStream.toString().contains("Your choice is exit!"));
        }
        finally {
            System.setOut(System.out);
            System.setIn(System.in);
        }
    }

    @Test
    public void showClientsTest(){
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(("3\n" +
                    "0\n").getBytes());
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream out = new PrintStream(outputStream);
            System.setOut(out);
            System.setIn(in);

            ClientWorkingMenu menu = new ClientWorkingMenu(service);
            menu.start();
            Assert.assertTrue(outputStream.toString().contains("List of all:"));
        }
        finally {
            System.setOut(System.out);
            System.setIn(System.in);
        }
    }

    @Test
    public void exitCommandTest(){

    }

}
