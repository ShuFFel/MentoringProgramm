package com.instinctools.egor.mentoring.JPA;

import com.instinctools.egor.mentoring.JPA.DAO.ClientDAO;
import com.instinctools.egor.mentoring.JPA.DAO.DAOImpl.ClientDAOImpl;
import com.instinctools.egor.mentoring.JPA.commandlinemenu.AccountWorkingMenu;
import com.instinctools.egor.mentoring.JPA.commandlinemenu.ClientWorkingMenu;
import com.instinctools.egor.mentoring.JPA.service.ClientService;
import com.instinctools.egor.mentoring.JPA.service.DAOServiceImpl.ClientServiceImpl;

public class Main {

    public static void main(String[] args) {
        ClientDAO clientDAO = new ClientDAOImpl();
        ClientService service = new ClientServiceImpl(clientDAO);
        AccountWorkingMenu accountWorkingMenu = new AccountWorkingMenu(service);
        ClientWorkingMenu clientWorkingMenu = new ClientWorkingMenu(service, accountWorkingMenu);
        clientWorkingMenu.start();
        System.exit(0);
    }
}
