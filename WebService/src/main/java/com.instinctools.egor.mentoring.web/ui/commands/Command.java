package com.instinctools.egor.mentoring.web.ui.commands;

import java.util.Date;

public abstract class Command {
    private String commandInformation;

    public Command() {
        commandInformation = "Command created"
                        + new Date(System.currentTimeMillis()).toString();
    }

    public abstract void execute();

    public abstract void undo();

    public abstract String showInfo();

    protected String getCommandInformation() {
        return commandInformation;
    }

    protected void setCommandInformation(final String commandInformation) {
        this.commandInformation = commandInformation;
    }
}
