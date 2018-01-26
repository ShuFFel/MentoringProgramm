package com.instinctools.egor.mentoring.web.ui.commands.user;

import com.instinctools.egor.mentoring.web.core.entity.User;
import com.instinctools.egor.mentoring.web.core.services.UserService;
import com.instinctools.egor.mentoring.web.ui.commands.Command;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CreateCommand extends Command {
    private static final Scanner SCANNER = new Scanner(System.in, StandardCharsets.UTF_8.displayName());
    private final UserService service;
    private User backup;

    public CreateCommand(final UserService service) {
        super();
        String commandInformation = getCommandInformation();
        setCommandInformation(commandInformation.concat(" Command type: Create\n"));
        this.service = service;
    }

    @Override
    public void execute() {
        String userName;
        System.out.println("input username: \n");
        userName = SCANNER.next();
        System.out.println("input date of birth(format dd-MM-yy): \n");
        String date = SCANNER.next();
        Date birthDate;
        try {
            birthDate = new SimpleDateFormat("dd-MM-yy").parse(date);
        } catch (ParseException e) {
            birthDate = new Date(System.currentTimeMillis());
            e.printStackTrace();
        }
        backup = new User(userName, birthDate);
        service.createUser(backup);
    }

    @Override
    public void undo() {
        service.deleteUser(backup);
    }

    @Override
    public String showInfo() {
        return getCommandInformation();
    }
}
