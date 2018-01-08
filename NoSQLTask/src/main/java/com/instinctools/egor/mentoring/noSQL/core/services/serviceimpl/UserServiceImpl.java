package com.instinctools.egor.mentoring.noSQL.core.services.serviceimpl;

import com.instinctools.egor.mentoring.noSQL.core.repository.BookRepository;
import com.instinctools.egor.mentoring.noSQL.core.repository.UserRepository;
import com.instinctools.egor.mentoring.noSQL.core.entity.Book;
import com.instinctools.egor.mentoring.noSQL.core.entity.User;
import com.instinctools.egor.mentoring.noSQL.core.services.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BookRepository bookRepository;

    public UserServiceImpl(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void createUser(User user) {
        this.userRepository.createUser(user);
    }

    @Override
    public void deleteUser(User user) {
        List<Book> userBooks = this.bookRepository.getBooksByOwnerId(user.getId());
        userBooks.forEach(book -> {
            user.removeBook(book);
            bookRepository.updateBook(book);
        });
        this.userRepository.deleteUser(user);
    }

    @Override
    public void updateUser(User user) {
        List<Book> userBooks = this.bookRepository.getBooksByOwnerId(user.getId());
        userBooks.forEach(book -> {
            user.addBook(book);
            bookRepository.updateBook(book);
        });
        this.userRepository.updateUser(user);
    }

    @Override
    public User getUserById(String id) {
        return userRepository.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
}
