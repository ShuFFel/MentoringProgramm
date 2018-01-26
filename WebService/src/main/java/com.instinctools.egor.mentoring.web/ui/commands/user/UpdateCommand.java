package com.instinctools.egor.mentoring.web.ui.commands.user;

import com.instinctools.egor.mentoring.web.core.entity.User;
import com.instinctools.egor.mentoring.web.core.services.UserService;
import com.instinctools.egor.mentoring.web.ui.commands.Command;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UpdateCommand extends Command {
    private static final Scanner SCANNER = new Scanner(System.in, StandardCharsets.UTF_8.displayName());
    private final UserService userService;
    private final User backupUser;

    public UpdateCommand(final UserService userService, final User userToUpdate) {
        super();
        String commandInformation = getCommandInformation();
        setCommandInformation(commandInformation.concat(" Command type: Update\n"));
        this.userService = userService;
        backupUser = userToUpdate;
    }

    @Override
    public void execute() {
        String userName;
        System.out.println("input new username: \n");
        userName = SCANNER.next();
        System.out.println("input new date of birth(format dd-MM-yy): \n");
        String date = SCANNER.next();
        Date birthDate;
        try {
            birthDate = new SimpleDateFormat("dd-MM-yy").parse(date);
        } catch (ParseException e) {
            birthDate = new Date(System.currentTimeMillis());
            e.printStackTrace();
        }
        User userToUpdate = new User(userName, birthDate);
        userToUpdate.setId(backupUser.getId());
        userService.updateUser(userToUpdate);
    }

    @Override
    public void undo() {
        userService.updateUser(backupUser);
    }

    @Override
    public String showInfo() {
        return getCommandInformation();
    }
}
