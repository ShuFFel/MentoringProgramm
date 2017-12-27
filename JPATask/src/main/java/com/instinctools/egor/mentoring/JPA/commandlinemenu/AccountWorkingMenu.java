package com.instinctools.egor.mentoring.JPA.commandlinemenu;

import com.instinctools.egor.mentoring.JPA.entity.Account;
import com.instinctools.egor.mentoring.JPA.entity.Client;
import com.instinctools.egor.mentoring.JPA.service.ClientService;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AccountWorkingMenu {
    private ClientService service;
    private Scanner scanner;
    private Client mainClient;

    public AccountWorkingMenu(ClientService clientService){
        this.service = clientService;
    }

    public void start(Client client) {
        this.mainClient = client;
        scanner = new Scanner(System.in);
        String chose;
        while(true) {
            System.out.println(mainClient.toString());
            System.out.println("Menu\n" +
                    "1 - create account\n" +
                    "2 - change account\n" +
                    "3 - delete account\n" +
                    "0 - go to the previous menu");
            chose = scanner.next();
            switch (chose){
                case "1":{
                    System.out.println("Input nickname: ");
                    String nickname = scanner.next();
                    System.out.println("Input date of birth(format dd-MM-yy): ");
                    String date = scanner.next();
                    Date birth_date;
                    try {
                        birth_date = new SimpleDateFormat("dd-MM-yy").parse(date);
                    } catch (ParseException e) {
                        birth_date = new Date(System.currentTimeMillis());
                        e.printStackTrace();
                    }
                    try {
                        mainClient = service.createAccount(mainClient.getUserId(), new Account(nickname, birth_date));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "2":{
                    Account chosenAccount = choseAccount();
                    if(chosenAccount == null) {
                        System.out.println("Your choice is exit!");
                        break;
                    }
                    System.out.println("Your Account: "+ chosenAccount.toString());
                    System.out.println("Input new nickname: ");
                    String nickname = scanner.next();
                    System.out.println("Input new date of birth(format dd-MM-yy): ");
                    String date = scanner.next();
                    Date birth_date;
                    try {
                        birth_date = new SimpleDateFormat("dd-MM-yy").parse(date);
                    } catch (ParseException e) {
                        birth_date = new Date(System.currentTimeMillis());
                        e.printStackTrace();
                    }
                    chosenAccount.setDateOfBirth(birth_date);
                    chosenAccount.setNickname(nickname);
                    try {
                        mainClient = service.updateClient(mainClient);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "3":{
                    Account chosenAccount = choseAccount();
                    if(chosenAccount == null){
                        System.out.println("Your choice is exit!");
                        break;
                    }
                    System.out.println("Deleted Account: "+ chosenAccount.toString());
                    try {
                        mainClient = service.deleteAccount(mainClient, chosenAccount);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "0":{
                    return;
                }
                default:{
                    System.out.println("Wrong operation!");
                    break;
                }
            }
        }
    }

    private Account choseAccount(){
        List<Account> accountList = mainClient.getAccounts();
        System.out.println("Chose Account: \n");
        int i = 1;
        for(Account account: accountList){
            System.out.println(i + " - " + account.getNickname() + " ID: " + account.getId() + "\n");
            i++;
        }
        System.out.println("0 - exit");
        String chose = scanner.next();
        if(Integer.parseInt(chose) > accountList.size() || Integer.parseInt(chose) == 0)return null;
        return accountList.get(Integer.parseInt(chose) - 1);
    }

}
