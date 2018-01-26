package com.instinctools.egor.mentoring.web.ui.commands.user;

import com.instinctools.egor.mentoring.web.core.entity.User;
import com.instinctools.egor.mentoring.web.core.services.UserService;
import com.instinctools.egor.mentoring.web.ui.commands.Command;

public class DeleteCommand extends Command {
    private final UserService userService;
    private final User user;
    private final User backupUser;

    public DeleteCommand(UserService userService, User user) {
        super();
        this.userService = userService;
        this.user = user;
        backupUser = user;
    }

    @Override
    public void execute() {
        userService.deleteUser(user);
    }

    @Override
    public void undo() {
        userService.createUser(backupUser);
    }

    @Override
    public String showInfo() {
        return commandInformation.concat(" Command type: Delete\n");
    }
}
