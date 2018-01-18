package com.instinctools.egor.mentoring.web.core.services.serviceimpl;

import com.instinctools.egor.mentoring.web.core.entity.Book;
import com.instinctools.egor.mentoring.web.core.entity.User;
import com.instinctools.egor.mentoring.web.core.repository.BookRepository;
import com.instinctools.egor.mentoring.web.core.repository.UserRepository;
import com.instinctools.egor.mentoring.web.core.services.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private UserRepository userRepository;
    private BookRepository bookRepository;

    public BookServiceImpl(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void createBook(Book book) {
        this.bookRepository.createBook(book);
    }

    @Override
    public void deleteBook(Book book) {
        User owner = book.getOwner();
        if (owner != null) {
            owner.removeBook(book);
        }
        this.bookRepository.deleteBook(book);
    }

    @Override
    public void updateBook(Book book) {
        bookRepository.updateBook(book);
    }

    @Override
    public void assignBook(User user, Book book) {
        User owner = book.getOwner();
        if (owner != null) {
            owner.removeBook(book);
            this.userRepository.updateUser(owner);
        }
        user.addBook(book);
        this.bookRepository.updateBook(book);
    }

    @Override
    public Book getBookById(String id) {
        return bookRepository.getBookById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return this.bookRepository.getAllBooks();
    }
}
