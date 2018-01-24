package com.instinctools.egor.mentoring.web.ui;

import com.instinctools.egor.mentoring.web.core.entity.User;
import com.instinctools.egor.mentoring.web.core.services.UserService;
import com.instinctools.egor.mentoring.web.ui.commands.Command;
import com.instinctools.egor.mentoring.web.ui.commands.user.CreateCommand;
import com.instinctools.egor.mentoring.web.ui.commands.user.DeleteCommand;
import com.instinctools.egor.mentoring.web.ui.commands.user.UpdateCommand;

import java.util.*;

public class UserWorkingMenu {
    private static Stack<Command> history = new Stack<Command>();
    private UserService userService;
    private Scanner scanner;
    private BookWorkingMenu bookMenu;

    public UserWorkingMenu(UserService userService, BookWorkingMenu bookMenu) {
        this.userService = userService;
        this.bookMenu = bookMenu;
    }

    public void start() {
        scanner = new Scanner(System.in);
        String chose;
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
                    executeCommand(new CreateCommand(userService));
                    break;
                }
                case "2": {
                    User mainUser = showUsers();
                    if (mainUser == null) {
                        System.out.println("Your choice is exit!");
                    } else {
                        startNextMenu(mainUser);
                    }
                    break;
                }
                case "3": {
                    User userToDelete = showUsers();
                    if (userToDelete == null) {
                        System.out.println("Your choice is exit!");
                    } else {
                        executeCommand(new DeleteCommand(userService, userToDelete));
                    }
                    break;
                }
                case "4": {
                    User userToUpdate = showUsers();
                    if (userToUpdate == null) {
                        System.out.println("Your choice is exit!");
                    } else {
                        executeCommand(new UpdateCommand(userService, userToUpdate));
                    }
                    break;
                }
                case "5": {
                    System.out.println(showHistory());
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

    private void executeCommand(Command command){
        command.execute();
        history.push(command);
    }

    private List<String> showHistory(){
        List<String> result = new ArrayList<>();
        history.forEach(command -> result.add(command.showInfo()));
        return result;
    }

    private void startNextMenu(User mainUser) {
        System.out.println("Your user: " + mainUser.toString());
        bookMenu.start(mainUser);
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
