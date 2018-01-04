package com.instinctools.egor.mentoring.noSQL.service.serviceImpl;

import com.instinctools.egor.mentoring.noSQL.DAO.BookDAO;
import com.instinctools.egor.mentoring.noSQL.DAO.UserDAO;
import com.instinctools.egor.mentoring.noSQL.entity.Book;
import com.instinctools.egor.mentoring.noSQL.entity.User;
import com.instinctools.egor.mentoring.noSQL.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private UserDAO userDAO;
    private BookDAO bookDAO;

    public BookServiceImpl(UserDAO userDAO, BookDAO bookDAO) {
        this.userDAO = userDAO;
        this.bookDAO = bookDAO;
    }

    @Override
    public void createBook(Book book) {
        this.bookDAO.createBook(book);
    }

    @Override
    public void deleteBook(Book book) {
        User owner = book.getOwner();
        if (owner != null) {
            owner.removeBook(book);
        }
        this.bookDAO.deleteBook(book);
    }

    @Override
    public void updateBook(Book book) {
        bookDAO.updateBook(book);
    }

    @Override
    public void assignBook(User user, Book book) {
        User owner = book.getOwner();
        if (owner != null) {
            owner.removeBook(book);
            this.userDAO.updateUser(owner);
        }
        user.addBook(book);
        this.bookDAO.updateBook(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return this.bookDAO.getAllBooks();
    }
}
