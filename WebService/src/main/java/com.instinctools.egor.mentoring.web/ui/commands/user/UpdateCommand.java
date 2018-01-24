package com.instinctools.egor.mentoring.web.ui.commands.user;

import com.instinctools.egor.mentoring.web.core.entity.User;
import com.instinctools.egor.mentoring.web.core.services.UserService;
import com.instinctools.egor.mentoring.web.ui.commands.Command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UpdateCommand extends Command {
    private static final Scanner SCANNER = new Scanner(System.in);
    private final UserService userService;
    private final User userToUpdate;

    public UpdateCommand(UserService userService, User userToUpdate) {
        super();
        this.userService = userService;
        this.userToUpdate = userToUpdate;
    }

    @Override
    public void execute() {
        String userName;
        System.out.println("input new username: \n");
        userName = SCANNER.next();
        System.out.println("input new date of birth(format dd-MM-yy): \n");
        String date = SCANNER.next();
        Date birth_date;
        try {
            birth_date = new SimpleDateFormat("dd-MM-yy").parse(date);
        } catch (ParseException e) {
            birth_date = new Date(System.currentTimeMillis());
            e.printStackTrace();
        }
        userToUpdate.setUserName(userName);
        userToUpdate.setDateOfBirth(birth_date);
        userService.updateUser(userToUpdate);
    }

    @Override
    public String showInfo() {
        return commandInformation.concat(" Command type: Update\n");
    }
}
