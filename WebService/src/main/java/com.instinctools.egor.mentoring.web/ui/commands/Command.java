package com.instinctools.egor.mentoring.web.ui.commands;

import java.util.Date;

public abstract class Command {
    protected String commandInformation;

    public Command() {
        commandInformation = "Command created" +
                new Date(System.currentTimeMillis()).toString();
    }

    public abstract void execute();

    public abstract String showInfo();
}
