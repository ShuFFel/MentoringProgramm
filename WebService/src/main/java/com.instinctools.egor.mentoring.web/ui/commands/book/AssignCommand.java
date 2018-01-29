package com.instinctools.egor.mentoring.web.ui.commands.book;

import com.instinctools.egor.mentoring.web.core.entity.Book;
import com.instinctools.egor.mentoring.web.core.entity.User;
import com.instinctools.egor.mentoring.web.core.services.BookService;
import com.instinctools.egor.mentoring.web.ui.commands.Command;

public class AssignCommand extends Command {
    private final BookService bookService;
    private final User userToAssign;
    private final Book bookToAssign;

    public AssignCommand(final BookService bookService, final User userToAssign, final Book bookToAssign) {
        super();
        this.bookService = bookService;
        this.userToAssign = userToAssign;
        this.bookToAssign = bookToAssign;
        String commandInformation = getCommandInformation();
        setCommandInformation(commandInformation.concat(" Command type: Assign\n"));
    }

    @Override
    public void execute() {
        bookService.assignBook(userToAssign, bookToAssign);
    }

    @Override
    public void undo() {
        bookService.assignBook(null, bookToAssign);
    }

    @Override
    public String showInfo() {
        return getCommandInformation();
    }
}
