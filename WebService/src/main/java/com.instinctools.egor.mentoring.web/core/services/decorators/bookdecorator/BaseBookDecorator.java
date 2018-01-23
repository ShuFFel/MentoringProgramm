package com.instinctools.egor.mentoring.web.core.services.decorators.bookdecorator;

import com.instinctools.egor.mentoring.web.core.entity.Book;
import com.instinctools.egor.mentoring.web.core.entity.User;
import com.instinctools.egor.mentoring.web.core.services.BookService;

import java.util.List;

public class BaseBookDecorator implements BookService {
    private BookService wrappee;

    public BaseBookDecorator(BookService wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public void createBook(Book book) {
        wrappee.createBook(book);
    }

    @Override
    public void deleteBook(Book book) {
        wrappee.deleteBook(book);
    }

    @Override
    public void updateBook(Book book) {
        wrappee.updateBook(book);
    }

    @Override
    public void assignBook(User user, Book book) {
        wrappee.assignBook(user, book);
    }

    @Override
    public Book getBookById(String id) {
        return wrappee.getBookById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return wrappee.getAllBooks();
    }
}
