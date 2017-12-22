package com.instinctools.egor.mentoring.JPA.Service.DAOServiceImpl;

import com.instinctools.egor.mentoring.JPA.DAO.ClientDAO;
import com.instinctools.egor.mentoring.JPA.DAO.DAOFactory.Factory;
import com.instinctools.egor.mentoring.JPA.Entity.Account;
import com.instinctools.egor.mentoring.JPA.Entity.Client;
import com.instinctools.egor.mentoring.JPA.Service.AccountService;

import java.sql.SQLException;

public class AccountServiceIpml implements AccountService {

    ClientDAO account;
    Client mainClient;

    public AccountServiceIpml(Client client){
        this.account = Factory.getInstance().getClientDAO();
        this.mainClient = client;
    }

    public void createAccount(Account account) {
        try {
            this.account.createAccount(mainClient, account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAccount(Account account) {
        try {
            this.account.deleteAccount(mainClient, account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAccount(Account account) {
        try {
            this.account.changeAccount(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
