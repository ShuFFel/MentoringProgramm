package com.instinctools.egor.mentoring.web.ui.commands.book;

import com.instinctools.egor.mentoring.web.core.entity.Book;
import com.instinctools.egor.mentoring.web.core.entity.User;
import com.instinctools.egor.mentoring.web.core.services.BookService;
import com.instinctools.egor.mentoring.web.ui.commands.Command;

public class DeleteCommand extends Command {
    private final BookService bookService;
    private final Book book;

    public DeleteCommand(BookService userService, Book book) {
        super();
        this.bookService = userService;
        this.book = book;
    }

    @Override
    public void execute() {
        bookService.deleteBook(book);
    }

    @Override
    public String showInfo() {
        return commandInformation.concat(" Command type: Delete\n");
    }
}
