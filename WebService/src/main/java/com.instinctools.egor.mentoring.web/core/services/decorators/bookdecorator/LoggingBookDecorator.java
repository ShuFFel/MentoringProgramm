package com.instinctools.egor.mentoring.web.core.services.decorators.bookdecorator;

import com.instinctools.egor.mentoring.web.core.entity.Book;
import com.instinctools.egor.mentoring.web.core.entity.User;
import com.instinctools.egor.mentoring.web.core.services.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class LoggingBookDecorator extends BaseBookDecorator {
    private static final Logger log = LogManager.getLogger();

    public LoggingBookDecorator(BookService wrappee) {
        super(wrappee);
    }

    @Override
    public void createBook(Book book) {
        super.createBook(book);
        log.info("Book: " + book.toString() + "\nCreated");
    }

    @Override
    public void deleteBook(Book book) {
        super.deleteBook(book);
        log.info("Book: " + book.toString() + "\nDeleted");
    }

    @Override
    public void updateBook(Book book) {
        super.updateBook(book);
        log.info("Book with id: " + book.getId() + " was updated\n" +
                "Result: " + book.toString());
    }

    @Override
    public void assignBook(User user, Book book) {
        super.assignBook(user, book);
        log.info("Book: " + book.toString() + " was assigned to user: " + user.getUserName());
    }

    @Override
    public Book getBookById(String id) {
        log.info("You look for book with id: " + id + "\n");
        Book bookById = super.getBookById(id);
        log.info("Found book: " + bookById.toString());
        return bookById;
    }

    @Override
    public List<Book> getAllBooks() {
        log.info("You look for list of all books");
        return super.getAllBooks();
    }
}
