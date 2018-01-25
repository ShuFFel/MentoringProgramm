package com.instinctools.egor.mentoring.web.core.services.decorators.bookdecorator;

import com.instinctools.egor.mentoring.web.core.entity.Book;
import com.instinctools.egor.mentoring.web.core.entity.User;
import com.instinctools.egor.mentoring.web.core.services.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class LoggingBookDecorator implements BookService {
    private static final Logger log = LoggerFactory.getLogger(LoggingBookDecorator.class);
    private BookService wrappee;

    public LoggingBookDecorator(BookService wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public void createBook(Book book) {
        wrappee.createBook(book);
        log.info("Book: " + book.toString() + "\nCreated");
    }

    @Override
    public void deleteBook(Book book) {
        wrappee.deleteBook(book);
        log.info("Book: " + book.toString() + "\nDeleted");
    }

    @Override
    public void updateBook(Book book) {
        wrappee.updateBook(book);
        log.info("Book with id: " + book.getId() + " was updated\n" +
                "Result: " + book.toString());
    }

    @Override
    public void assignBook(User user, Book book) {
        wrappee.assignBook(user, book);
        log.info("Book: " + book.toString() + " was assigned to user: " + user.getUserName());
    }

    @Override
    public Book getBookById(String id) {
        log.info("You look for book with id: " + id + "\n");
        Book bookById = wrappee.getBookById(id);
        log.info("Found book: " + bookById.toString());
        return bookById;
    }

    @Override
    public List<Book> getAllBooks() {
        log.info("You look for list of all books");
        return wrappee.getAllBooks();
    }
}
