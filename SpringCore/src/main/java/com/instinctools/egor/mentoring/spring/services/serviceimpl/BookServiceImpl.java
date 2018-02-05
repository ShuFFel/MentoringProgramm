package com.instinctools.egor.mentoring.spring.services.serviceimpl;

import com.instinctools.egor.mentoring.spring.core.entity.Book;
import com.instinctools.egor.mentoring.spring.core.entity.User;
import com.instinctools.egor.mentoring.spring.core.repository.BookRepository;
import com.instinctools.egor.mentoring.spring.core.repository.UserRepository;
import com.instinctools.egor.mentoring.spring.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    @Override
    public void createBook(final Book book) {
        this.bookRepository.createBook(book);
    }

    @Transactional
    @Override
    public void deleteBook(final Book book) {
        this.bookRepository.deleteBook(book.getId());
    }

    @Transactional
    @Override
    public void updateBook(final Book book) {
        bookRepository.updateBook(book);
    }

    @Transactional
    @Override
    public void assignBook(final User user, final Book book) {
        user.addBook(book);
        this.userRepository.updateUser(user);
        this.bookRepository.updateBook(book);
    }

    @Transactional
    @Override
    public Book getBookById(final String id) {
        return bookRepository.getBookById(id);
    }

    @Transactional
    @Override
    public List<Book> getAllBooks() {
        return this.bookRepository.getAllBooks();
    }
}
