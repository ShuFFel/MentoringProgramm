package com.instinctools.egor.mentoring.spring.services.serviceimpl;

import com.instinctools.egor.mentoring.spring.core.entity.Book;
import com.instinctools.egor.mentoring.spring.core.entity.User;
import com.instinctools.egor.mentoring.spring.core.repository.BookRepository;
import com.instinctools.egor.mentoring.spring.core.repository.UserRepository;
import com.instinctools.egor.mentoring.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public UserServiceImpl(final UserRepository userRepository, final BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void createUser(final User user) {
        this.userRepository.createUser(user);
    }

    @Override
    public void deleteUser(final User user) {
        List<Book> userBooks = this.bookRepository.getBooksByOwnerId(user.getId());
        userBooks.forEach(book -> {
            user.removeBook(book);
            bookRepository.updateBook(book);
        });
        this.userRepository.deleteUser(user);
    }

    @Override
    public void updateUser(final User user) {
        List<Book> userBooks = this.bookRepository.getBooksByOwnerId(user.getId());
        userBooks.forEach(book -> {
            user.addBook(book);
            bookRepository.updateBook(book);
        });
        this.userRepository.updateUser(user);
    }

    @Override
    public User getUserById(final String id) {
        return userRepository.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
}
