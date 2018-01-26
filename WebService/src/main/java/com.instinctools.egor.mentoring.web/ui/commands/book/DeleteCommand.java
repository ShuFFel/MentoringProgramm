package com.instinctools.egor.mentoring.web.ui.commands.book;

import com.instinctools.egor.mentoring.web.core.entity.Book;
import com.instinctools.egor.mentoring.web.core.services.BookService;
import com.instinctools.egor.mentoring.web.ui.commands.Command;

public class DeleteCommand extends Command {
    private final BookService bookService;
    private final Book book;
    private final Book backup;

    public DeleteCommand(final BookService userService, final Book book) {
        super();
        String commandInformation = getCommandInformation();
        setCommandInformation(commandInformation.concat(" Command type: Delete\n"));
        this.bookService = userService;
        this.book = book;
        backup = book;
    }

    @Override
    public void execute() {
        bookService.deleteBook(book);
    }

    @Override
    public void undo() {
        bookService.createBook(backup);
    }

    @Override
    public String showInfo() {
        return getCommandInformation();
    }
}
