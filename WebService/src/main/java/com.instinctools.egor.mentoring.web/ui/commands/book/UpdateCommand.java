package com.instinctools.egor.mentoring.web.ui.commands.book;

import com.instinctools.egor.mentoring.web.core.entity.Book;
import com.instinctools.egor.mentoring.web.core.services.BookService;
import com.instinctools.egor.mentoring.web.ui.commands.Command;

import java.util.Scanner;

public class UpdateCommand extends Command {
    private static final Scanner SCANNER = new Scanner(System.in);
    private final BookService bookService;
    private final Book bookToUpDate;

    public UpdateCommand(BookService bookService, Book bookToUpDate) {
        super();
        this.bookService = bookService;
        this.bookToUpDate = bookToUpDate;
    }

    @Override
    public void execute() {
        System.out.println("Your Book: " + bookToUpDate.toString());
        System.out.println("Input new name of book: ");
        String bookName = SCANNER.next();
        System.out.println("Input new author: ");
        String author = SCANNER.next();
        bookToUpDate.setAuthor(author);
        bookToUpDate.setName(bookName);
        bookService.updateBook(bookToUpDate);
    }

    @Override
    public String showInfo() {
        return commandInformation.concat(" Command type: Update\n");
    }
}
