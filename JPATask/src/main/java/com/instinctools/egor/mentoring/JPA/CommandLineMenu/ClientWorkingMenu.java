package com.instinctools.egor.mentoring.JPA.CommandLineMenu;

import com.instinctools.egor.mentoring.JPA.Entity.Account;
import com.instinctools.egor.mentoring.JPA.Entity.Client;
import com.instinctools.egor.mentoring.JPA.Service.ClientService;
import com.instinctools.egor.mentoring.JPA.Service.DAOServiceImpl.ClientServiceImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ClientWorkingMenu {

    private ClientService clientService;
    private Scanner scanner;

    public ClientWorkingMenu(ClientService clientService){
        this.clientService = clientService;
    }
    public void start(){
        scanner = new Scanner(System.in);
        String chose;
        String login, password;
        do{
            System.out.println("Menu: \n" +
                    "1 - create client\n" +
                    "2 - work with existing client\n" +
                    "3 - show list of all accounts\n" +
                    "4 - delete client\n" +
                    "0 - exit");
            chose = scanner.next();
            switch(chose){
                case "1": {
                    System.out.println("input username: \n");
                    login = scanner.next();
                    System.out.println("input password: \n");
                    password = scanner.next();
                    try {
                        clientService.createClient(new Client(login, password));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "2": {
                    Client mainClient = showClients();
                    if(mainClient == null){
                        System.out.println("Your choice is exit!");
                        break;
                    }
                    System.out.println("Your client: "+ mainClient.toString());
                    AccountWorkingMenu accountMenu = new AccountWorkingMenu(clientService);
                    accountMenu.start(mainClient);
                    break;
                }
                case "3": {
                    displayListOfAllAccounts();
                    break;
                }
                case "4":{
                    Client clientToDelete = showClients();
                    if(clientToDelete == null)break;
                    try {
                        clientService.deleteClient(clientToDelete);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "0":{
                    scanner.close();
                    System.exit(0);
                }
                default:{
                    System.out.println("Wrong operation!");
                    break;
                }
            }
        }while(true);
    }

    private void displayListOfAllAccounts(){
        List<Client> clients = clientService.getAllClients();
        List<Account> accounts;
        System.out.println("List of all: \n");
        for( Client client : clients){
            System.out.println(client.toString());
            accounts = client.getAccounts();
            for(Account account: accounts){
                System.out.println(account.toString());
            }
        }
    }

    private Client showClients(){
        List<Client> clients = clientService.getAllClients();
        System.out.println("Chose client: ");
        int i = 1;
        for(Client client: clients){
            System.out.println(i + " - " + client.toString());
            i++;
        }
        System.out.println("0 - exit");
        String chose = scanner.next();
        if(Integer.parseInt(chose) > clients.size() || Integer.parseInt(chose) == 0)return null;
        return clients.get(Integer.parseInt(chose) - 1);
    }
}
