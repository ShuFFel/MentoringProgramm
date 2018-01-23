package com.instinctools.egor.mentoring.web.core.services.serviceimpl;

import com.instinctools.egor.mentoring.web.core.entity.Book;
import com.instinctools.egor.mentoring.web.core.entity.User;
import com.instinctools.egor.mentoring.web.core.factory.RepositoryFactory;
import com.instinctools.egor.mentoring.web.core.services.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private RepositoryFactory repositoryFactory;

    public BookServiceImpl(RepositoryFactory factory) {
        this.repositoryFactory = factory;
    }

    @Override
    public void createBook(Book book) {
        this.repositoryFactory.getBookRepo().createBook(book);
    }

    @Override
    public void deleteBook(Book book) {
        User owner = book.getOwner();
        if (owner != null) {
            owner.removeBook(book);
        }
        this.repositoryFactory.getBookRepo().deleteBook(book);
    }

    @Override
    public void updateBook(Book book) {
        repositoryFactory.getBookRepo().updateBook(book);
    }

    @Override
    public void assignBook(User user, Book book) {
        User owner = book.getOwner();
        if (owner != null) {
            owner.removeBook(book);
            this.repositoryFactory.getUserRepo().updateUser(owner);
        }
        user.addBook(book);
        this.repositoryFactory.getBookRepo().updateBook(book);
    }

    @Override
    public Book getBookById(String id) {
        return repositoryFactory.getBookRepo().getBookById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return this.repositoryFactory.getBookRepo().getAllBooks();
    }
}
