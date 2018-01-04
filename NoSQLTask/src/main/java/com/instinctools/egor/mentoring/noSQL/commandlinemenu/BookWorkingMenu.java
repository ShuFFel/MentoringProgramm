package com.instinctools.egor.mentoring.noSQL.commandlinemenu;

import com.instinctools.egor.mentoring.noSQL.entity.Book;
import com.instinctools.egor.mentoring.noSQL.entity.User;
import com.instinctools.egor.mentoring.noSQL.factory.EntityFactory;
import com.instinctools.egor.mentoring.noSQL.service.BookService;

import java.util.List;
import java.util.Scanner;

public class BookWorkingMenu {

    private BookService bookService;
    private EntityFactory entityFactory;
    private Scanner scanner;

    public BookWorkingMenu(BookService bookService, EntityFactory entityFactory) {
        this.bookService = bookService;
        this.entityFactory = entityFactory;
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
                    System.out.println("Input name of book: ");
                    String bookName = scanner.next();
                    System.out.println("Input author: ");
                    String author = scanner.next();
                    bookService.createBook(entityFactory.createBook(bookName, author));
                    break;
                }
                case "2": {
                    Book chosenBook = choseBook();
                    if (chosenBook == null) {
                        System.out.println("Your choice is exit!");
                        break;
                    }
                    System.out.println("Your Book: " + chosenBook.toString());
                    System.out.println("Input new name of book: ");
                    String bookName = scanner.next();
                    System.out.println("Input new author: ");
                    String author = scanner.next();
                    chosenBook.setAuthor(author);
                    chosenBook.setName(bookName);
                    bookService.updateBook(chosenBook);
                    break;
                }
                case "3": {
                    Book chosenBook = choseBook();
                    if (chosenBook == null) {
                        System.out.println("Your choice is exit!");
                        break;
                    }
                    System.out.println("Deleted book: " + chosenBook.toString());
                    bookService.deleteBook(chosenBook);
                    break;
                }
                case "4": {
                    Book chosenBook = choseBook();
                    if (chosenBook == null) {
                        System.out.println("Your choice is exit!");
                        break;
                    }
                    bookService.assignBook(user, chosenBook);
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
