package com.instinctools.egor.mentoring.noSQL.service.serviceImpl;

import com.instinctools.egor.mentoring.noSQL.DAO.BookDAO;
import com.instinctools.egor.mentoring.noSQL.DAO.UserDAO;
import com.instinctools.egor.mentoring.noSQL.entity.Book;
import com.instinctools.egor.mentoring.noSQL.entity.User;
import com.instinctools.egor.mentoring.noSQL.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;
    private BookDAO bookDAO;

    public UserServiceImpl(UserDAO userDAO, BookDAO bookDAO) {
        this.userDAO = userDAO;
        this.bookDAO = bookDAO;
    }

    @Override
    public void createUser(User user) {
        this.userDAO.createUser(user);
    }

    @Override
    public void deleteUser(User user) {
        List<Book> userBooks = this.bookDAO.getBooksByOwnerId(user.get_id());
        userBooks.forEach(book -> {
            user.removeBook(book);
            bookDAO.updateBook(book);
        });
        this.userDAO.deleteUser(user);
    }

    @Override
    public void updateUser(User user) {
        List<Book> userBooks = this.bookDAO.getBooksByOwnerId(user.get_id());
        userBooks.forEach(book -> {
            user.addBook(book);
            bookDAO.updateBook(book);
        });
        this.userDAO.updateUser(user);
    }

    @Override
    public User getUserById(String id) {
        return userDAO.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
}
