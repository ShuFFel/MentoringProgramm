package com.instinctools.egor.mentoring.spring.services.serviceimpl;

import com.instinctools.egor.mentoring.spring.core.entity.Book;
import com.instinctools.egor.mentoring.spring.core.entity.User;
import com.instinctools.egor.mentoring.spring.core.repository.BookRepository;
import com.instinctools.egor.mentoring.spring.core.repository.UserRepository;
import com.instinctools.egor.mentoring.spring.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void createBook(final Book book) {
        this.bookRepository.createBook(book);
    }

    @Override
    public void deleteBook(final Book book) {
        this.bookRepository.deleteBook(book.getId());
    }

    @Override
    public void updateBook(final Book book) {
        bookRepository.updateBook(book);
    }

    @Override
    public void assignBook(final User user, final Book book) {
        user.addBook(book);
        this.userRepository.updateUser(user);
        this.bookRepository.updateBook(book);
    }

    @Override
    public void returnBook(final Book book) {
        book.setOwner(null);
        bookRepository.updateBook(book);
    }

    @Override
    public Book getBookById(final String id) {
        return bookRepository.getBookById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return this.bookRepository.getAllBooks();
    }
}
