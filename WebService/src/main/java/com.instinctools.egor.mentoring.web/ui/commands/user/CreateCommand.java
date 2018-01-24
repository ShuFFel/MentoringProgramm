package com.instinctools.egor.mentoring.web.ui.commands.user;

import com.instinctools.egor.mentoring.web.core.entity.User;
import com.instinctools.egor.mentoring.web.core.services.UserService;
import com.instinctools.egor.mentoring.web.ui.commands.Command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CreateCommand extends Command {
    private static final Scanner SCANNER = new Scanner(System.in);
    private final UserService service;

    public CreateCommand(UserService service) {
        super();
        this.service = service;
    }

    @Override
    public void execute() {
        String userName;
        System.out.println("input username: \n");
        userName = SCANNER.next();
        System.out.println("input date of birth(format dd-MM-yy): \n");
        String date = SCANNER.next();
        Date birth_date;
        try {
            birth_date = new SimpleDateFormat("dd-MM-yy").parse(date);
        } catch (ParseException e) {
            birth_date = new Date(System.currentTimeMillis());
            e.printStackTrace();
        }
        service.createUser(new User(userName, birth_date));
    }

    @Override
    public String showInfo() {
        return commandInformation.concat(" Command type: Create\n");
    }
}
