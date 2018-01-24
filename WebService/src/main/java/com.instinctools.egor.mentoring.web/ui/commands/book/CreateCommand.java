package com.instinctools.egor.mentoring.web.ui.commands.book;

import com.instinctools.egor.mentoring.web.core.entity.Book;
import com.instinctools.egor.mentoring.web.core.entity.User;
import com.instinctools.egor.mentoring.web.core.services.BookService;
import com.instinctools.egor.mentoring.web.core.services.UserService;
import com.instinctools.egor.mentoring.web.ui.commands.Command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CreateCommand extends Command {
    private static final Scanner SCANNER = new Scanner(System.in);
    private final BookService service;

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
        service.createBook(new Book(bookName, author));
    }

    @Override
    public String showInfo() {
        return commandInformation.concat(" Command type: Create\n");
    }
}
