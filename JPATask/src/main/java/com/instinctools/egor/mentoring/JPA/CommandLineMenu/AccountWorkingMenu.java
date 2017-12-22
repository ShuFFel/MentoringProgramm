package com.instinctools.egor.mentoring.JPA.CommandLineMenu;

import com.instinctools.egor.mentoring.JPA.Entity.Account;
import com.instinctools.egor.mentoring.JPA.Entity.Client;
import com.instinctools.egor.mentoring.JPA.Service.AccountService;
import com.instinctools.egor.mentoring.JPA.Service.DAOServiceImpl.AccountServiceIpml;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AccountWorkingMenu {
    private AccountService service;
    private Scanner scanner;
    private Client mainClient;

    public AccountWorkingMenu(Client client){
        this.mainClient = client;
        this.service = new AccountServiceIpml(client);
    }

    public void start(){
        scanner = new Scanner(System.in);
        int chose;
        while(true) {
            System.out.println(mainClient.toString());
            System.out.println("Menu\n" +
                    "1 - create account\n" +
                    "2 - change account\n" +
                    "3 - delete account\n" +
                    "0 - go to the previous menu");
            chose = scanner.nextInt();
            switch (chose){
                case 1:{
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
                    service.createAccount(new Account(nickname, birth_date));
                    break;
                }
                case 2:{
                    Account chosenAccount = choseAccount();
                    if(chosenAccount == null)break;
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
                    service.updateAccount(chosenAccount);
                    break;
                }
                case 3:{
                    Account chosenAccount = choseAccount();
                    if(chosenAccount == null)break;
                    System.out.println("Deleted Account: "+ chosenAccount.toString());
                    service.deleteAccount(chosenAccount);
                    break;
                }
                case 0:{
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
        int chose = scanner.nextInt();
        return (chose != 0)?accountList.get(chose - 1):null;
    }

}
