package com.instinctools.egor.mentoring.web.core.services.serviceimpl;

import com.instinctools.egor.mentoring.web.core.entity.Book;
import com.instinctools.egor.mentoring.web.core.entity.User;
import com.instinctools.egor.mentoring.web.core.factory.ServiceFactory;
import com.instinctools.egor.mentoring.web.core.services.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private ServiceFactory serviceFactory;

    public UserServiceImpl(ServiceFactory factory) {
        this.serviceFactory = factory;
    }

    @Override
    public void createUser(User user) {
        this.serviceFactory.getUserRepo().createUser(user);
    }

    @Override
    public void deleteUser(User user) {
        List<Book> userBooks = this.serviceFactory.getBookRepo().getBooksByOwnerId(user.getId());
        userBooks.forEach(book -> {
            user.removeBook(book);
            serviceFactory.getBookRepo().updateBook(book);
        });
        this.serviceFactory.getUserRepo().deleteUser(user);
    }

    @Override
    public void updateUser(User user) {
        List<Book> userBooks = this.serviceFactory.getBookRepo().getBooksByOwnerId(user.getId());
        userBooks.forEach(book -> {
            user.addBook(book);
            serviceFactory.getBookRepo().updateBook(book);
        });
        this.serviceFactory.getUserRepo().updateUser(user);
    }

    @Override
    public User getUserById(String id) {
        return serviceFactory.getUserRepo().getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return serviceFactory.getUserRepo().getAllUsers();
    }
}
