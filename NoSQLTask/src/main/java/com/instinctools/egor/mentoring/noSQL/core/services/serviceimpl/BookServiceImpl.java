package com.instinctools.egor.mentoring.noSQL.core.services.serviceimpl;

import com.instinctools.egor.mentoring.noSQL.core.repository.BookRepository;
import com.instinctools.egor.mentoring.noSQL.core.repository.UserRepository;
import com.instinctools.egor.mentoring.noSQL.core.entity.Book;
import com.instinctools.egor.mentoring.noSQL.core.entity.User;
import com.instinctools.egor.mentoring.noSQL.core.services.BookService;

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
    public List<Book> getAllBooks() {
        return this.bookRepository.getAllBooks();
    }
}
