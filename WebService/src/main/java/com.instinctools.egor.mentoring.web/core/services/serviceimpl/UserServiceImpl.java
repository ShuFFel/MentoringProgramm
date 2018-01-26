package com.instinctools.egor.mentoring.web.core.services.serviceimpl;

import com.instinctools.egor.mentoring.web.core.entity.Book;
import com.instinctools.egor.mentoring.web.core.entity.User;
import com.instinctools.egor.mentoring.web.core.factory.RepositoryFactory;
import com.instinctools.egor.mentoring.web.core.services.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private RepositoryFactory repositoryFactory;

    public UserServiceImpl(final RepositoryFactory factory) {
        this.repositoryFactory = factory;
    }

    @Override
    public void createUser(final User user) {
        this.repositoryFactory.getUserRepo().createUser(user);
    }

    @Override
    public void deleteUser(final User user) {
        List<Book> userBooks = this.repositoryFactory.getBookRepo().getBooksByOwnerId(user.getId());
        userBooks.forEach(book -> {
            user.removeBook(book);
            repositoryFactory.getBookRepo().updateBook(book);
        });
        this.repositoryFactory.getUserRepo().deleteUser(user);
    }

    @Override
    public void updateUser(final User user) {
        List<Book> userBooks = this.repositoryFactory.getBookRepo().getBooksByOwnerId(user.getId());
        userBooks.forEach(book -> {
            user.addBook(book);
            repositoryFactory.getBookRepo().updateBook(book);
        });
        this.repositoryFactory.getUserRepo().updateUser(user);
    }

    @Override
    public User getUserById(final String id) {
        return repositoryFactory.getUserRepo().getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return repositoryFactory.getUserRepo().getAllUsers();
    }
}
