package com.instinctools.egor.mentoring.web.core.services.serviceimpl;

import com.instinctools.egor.mentoring.web.core.entity.Book;
import com.instinctools.egor.mentoring.web.core.entity.User;
import com.instinctools.egor.mentoring.web.core.factory.ServiceFactory;
import com.instinctools.egor.mentoring.web.core.services.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private ServiceFactory serviceFactory;

    public BookServiceImpl(ServiceFactory factory) {
        this.serviceFactory = factory;
    }

    @Override
    public void createBook(Book book) {
        this.serviceFactory.getBookRepo().createBook(book);
    }

    @Override
    public void deleteBook(Book book) {
        User owner = book.getOwner();
        if (owner != null) {
            owner.removeBook(book);
        }
        this.serviceFactory.getBookRepo().deleteBook(book);
    }

    @Override
    public void updateBook(Book book) {
        serviceFactory.getBookRepo().updateBook(book);
    }

    @Override
    public void assignBook(User user, Book book) {
        User owner = book.getOwner();
        if (owner != null) {
            owner.removeBook(book);
            this.serviceFactory.getUserRepo().updateUser(owner);
        }
        user.addBook(book);
        this.serviceFactory.getBookRepo().updateBook(book);
    }

    @Override
    public Book getBookById(String id) {
        return serviceFactory.getBookRepo().getBookById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return this.serviceFactory.getBookRepo().getAllBooks();
    }
}
