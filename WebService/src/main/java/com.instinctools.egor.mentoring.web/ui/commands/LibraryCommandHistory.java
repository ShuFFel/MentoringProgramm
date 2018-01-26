package com.instinctools.egor.mentoring.web.ui.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public final class LibraryCommandHistory {
    private Stack<Command> history;
    private static LibraryCommandHistory ourInstance = new LibraryCommandHistory();

    public static LibraryCommandHistory getInstance() {
        return ourInstance;
    }

    private LibraryCommandHistory() {
        history = new Stack<>();
    }

    public void push(Command command) {
        history.push(command);
    }

    public Command pop() {
        return history.pop();
    }

    public List<String> getHistoryInfo() {
        List<String> info = new ArrayList<>();
        history.forEach(command -> info.add(command.showInfo()));
        return info;
    }
}
