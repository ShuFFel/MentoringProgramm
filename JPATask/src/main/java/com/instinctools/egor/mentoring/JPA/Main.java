package com.instinctools.egor.mentoring.JPA;

import com.instinctools.egor.mentoring.JPA.CommandLineMenu.ClientWorkingMenu;
import com.instinctools.egor.mentoring.JPA.Service.DAOServiceImpl.ClientServiceImpl;

public class Main {

    public static void main(String[] args) {
        ClientWorkingMenu menu =new ClientWorkingMenu(new ClientServiceImpl());
        menu.start();
    }

}
