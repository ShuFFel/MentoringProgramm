package com.instinctools.egor.mentoring.web.ui.commands.book;

import com.instinctools.egor.mentoring.web.core.entity.Book;
import com.instinctools.egor.mentoring.web.core.services.BookService;
import com.instinctools.egor.mentoring.web.ui.commands.Command;

import java.util.Scanner;

public class CreateCommand extends Command {
    private static final Scanner SCANNER = new Scanner(System.in);
    private final BookService service;
    private Book backup;

    public CreateCommand(BookService service) {
        super();
        this.service = service;
    }

    @Override
    public void execute() {
        System.out.println("Input name of book: ");
        String bookName = SCANNER.next();
        System.out.println("Input author: ");
        String author = SCANNER.next();
        backup = new Book(bookName, author);
        service.createBook(backup);
    }

    @Override
    public void undo() {
        service.deleteBook(backup);
    }

    @Override
    public String showInfo() {
        return commandInformation.concat(" Command type: Create\n");
    }
}
