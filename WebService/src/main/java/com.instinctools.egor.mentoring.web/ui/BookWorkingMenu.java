package com.instinctools.egor.mentoring.web.ui;

import com.instinctools.egor.mentoring.web.core.entity.Book;
import com.instinctools.egor.mentoring.web.core.entity.User;
import com.instinctools.egor.mentoring.web.core.services.BookService;
import com.instinctools.egor.mentoring.web.ui.commands.Command;
import com.instinctools.egor.mentoring.web.ui.commands.book.AssignCommand;
import com.instinctools.egor.mentoring.web.ui.commands.book.CreateCommand;
import com.instinctools.egor.mentoring.web.ui.commands.book.DeleteCommand;
import com.instinctools.egor.mentoring.web.ui.commands.book.UpdateCommand;
import com.sun.istack.NotNull;

import javax.sound.midi.Soundbank;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class BookWorkingMenu {
    private static Stack<Command> commands = new Stack<>();
    private BookService bookService;
    private Scanner scanner;

    public BookWorkingMenu(BookService bookService) {
        this.bookService = bookService;
    }

    public void start(User user) {
        scanner = new Scanner(System.in);
        String chose;
        while (true) {
            System.out.println("Menu\n" +
                    "1 - create book\n" +
                    "2 - change book\n" +
                    "3 - delete book\n" +
                    "4 - take book\n" +
                    "0 - go to the previous menu");
            chose = scanner.next();
            switch (chose) {
                case "1": {
                    executeCommand(new CreateCommand(bookService));
                    break;
                }
                case "2": {
                    Book chosenBook = choseBook();
                    if (chosenBook == null) {
                        System.out.println("Your choice is exit!");
                    } else {
                        executeCommand(new UpdateCommand(bookService, chosenBook));
                    }
                    break;
                }
                case "3": {
                    Book chosenBook = choseBook();
                    if (chosenBook == null) {
                        System.out.println("Your choice is exit!");
                    } else {
                        executeCommand(new DeleteCommand(bookService, chosenBook));
                    }
                    break;
                }
                case "4": {
                    Book chosenBook = choseBook();
                    if (chosenBook == null) {
                        System.out.println("Your choice is exit!");
                    } else {
                        executeCommand(new AssignCommand(bookService, user, chosenBook));
                    }
                    break;
                }
                case "5":{
                    showHistory();
                    break;
                }
                case "0": {
                    return;
                }
                default: {
                    System.out.println("Wrong operation!");
                    break;
                }
            }
        }
    }

    private void executeCommand(Command command) {
        command.execute();
        commands.push(command);
    }

    private void showHistory() {
        System.out.println(commands);
    }

    private Book choseBook() {
        List<Book> bookList = bookService.getAllBooks();
        System.out.println("Chose Book: \n");
        int i = 1;
        for (Book book : bookList) {
            System.out.println(i + " - " + book.toString());
            i++;
        }
        System.out.println("0 - exit");
        String chose = scanner.next();
        if (Integer.parseInt(chose) > bookList.size() || Integer.parseInt(chose) == 0) return null;
        return bookList.get(Integer.parseInt(chose) - 1);
    }
}
