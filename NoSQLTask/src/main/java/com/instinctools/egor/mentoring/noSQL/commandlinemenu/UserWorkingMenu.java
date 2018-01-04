package com.instinctools.egor.mentoring.noSQL.commandlinemenu;

import com.instinctools.egor.mentoring.noSQL.entity.User;
import com.instinctools.egor.mentoring.noSQL.factory.EntityFactory;
import com.instinctools.egor.mentoring.noSQL.service.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class UserWorkingMenu {

    private UserService userService;
    private Scanner scanner;
    private BookWorkingMenu bookMenu;
    private EntityFactory entityFactory;

    public UserWorkingMenu(UserService userService, BookWorkingMenu bookMenu, EntityFactory entityFactory) {
        this.userService = userService;
        this.bookMenu = bookMenu;
        this.entityFactory = entityFactory;
    }

    public void start() {
        scanner = new Scanner(System.in);
        String chose;
        String userName;
        do {
            System.out.println("Menu: \n" +
                    "1 - create user\n" +
                    "2 - work with existing user\n" +
                    "3 - delete user\n" +
                    "4 - change user\n" +
                    "0 - exit");
            chose = scanner.next();
            switch (chose) {
                case "1": {
                    System.out.println("input username: \n");
                    userName = scanner.next();
                    System.out.println("input date of birth(format dd-MM-yy): \n");
                    String date = scanner.next();
                    Date birth_date;
                    try {
                        birth_date = new SimpleDateFormat("dd-MM-yy").parse(date);
                    } catch (ParseException e) {
                        birth_date = new Date(System.currentTimeMillis());
                        e.printStackTrace();
                    }
                    userService.createUser(entityFactory.createUser(userName, birth_date));
                    break;
                }
                case "2": {
                    User mainUser = showUsers();
                    if (mainUser == null) {
                        System.out.println("Your choice is exit!");
                        break;
                    }
                    System.out.println("Your user: " + mainUser.toString());
                    bookMenu.start(mainUser);
                    break;
                }
                case "3": {
                    User userToDelete = showUsers();
                    if (userToDelete == null) {
                        System.out.println("Your choice is exit!");
                        break;
                    }
                    userService.deleteUser(userToDelete);
                    System.out.println(userToDelete.toString() + "\nDeleted!");
                    break;
                }
                case "4": {
                    User userToUpdate = showUsers();
                    if (userToUpdate == null) {
                        System.out.println("Your choice is exit!");
                        break;
                    }
                    System.out.println("input new username: \n");
                    userName = scanner.next();
                    System.out.println("input new date of birth(format dd-MM-yy): \n");
                    String date = scanner.next();
                    Date birth_date;
                    try {
                        birth_date = new SimpleDateFormat("dd-MM-yy").parse(date);
                    } catch (ParseException e) {
                        birth_date = new Date(System.currentTimeMillis());
                        e.printStackTrace();
                    }
                    userToUpdate.setUser_name(userName);
                    userToUpdate.setDate_of_birth(birth_date);
                    userService.updateUser(userToUpdate);
                    break;
                }
                case "0": {
                    scanner.close();
                    System.out.println("EXITED");
                    return;
                }
                default: {
                    System.out.println("Wrong operation!");
                    break;
                }
            }
        } while (true);
    }

    private User showUsers() {
        List<User> users = userService.getAllUsers();
        System.out.println("Chose user: ");
        int i = 1;
        for (User user : users) {
            System.out.println(i + " - " + user.toString());
            i++;
        }
        System.out.println("0 - exit");
        String chose = scanner.next();
        if (Integer.parseInt(chose) > users.size() || Integer.parseInt(chose) == 0) return null;
        return users.get(Integer.parseInt(chose) - 1);
    }
}
