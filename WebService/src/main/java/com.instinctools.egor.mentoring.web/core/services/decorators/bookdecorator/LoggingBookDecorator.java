package com.instinctools.egor.mentoring.web.core.services.decorators.bookdecorator;

import com.instinctools.egor.mentoring.web.core.entity.Book;
import com.instinctools.egor.mentoring.web.core.entity.User;
import com.instinctools.egor.mentoring.web.core.services.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class LoggingBookDecorator implements BookService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingBookDecorator.class);
    private BookService wrappee;

    public LoggingBookDecorator(final BookService wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public void createBook(final Book book) {
        wrappee.createBook(book);
        LOGGER.info("Book: " + book.toString() + "\nCreated");
    }

    @Override
    public void deleteBook(final Book book) {
        wrappee.deleteBook(book);
        LOGGER.info("Book: " + book.toString() + "\nDeleted");
    }

    @Override
    public void updateBook(final Book book) {
        wrappee.updateBook(book);
        LOGGER.info("Book with id: " + book.getId() + " was updated\n" +
                        "Result: " + book.toString());
    }

    @Override
    public void assignBook(final User user, final Book book) {
        wrappee.assignBook(user, book);
        LOGGER.info("Book: " + book.toString() + " was assigned to user: " + user.getUserName());
    }

    @Override
    public Book getBookById(final String id) {
        LOGGER.info("You look for book with id: " + id + "\n");
        Book bookById = wrappee.getBookById(id);
        LOGGER.info("Found book: " + bookById.toString());
        return bookById;
    }

    @Override
    public List<Book> getAllBooks() {
        LOGGER.info("You look for list of all books");
        return wrappee.getAllBooks();
    }
}
