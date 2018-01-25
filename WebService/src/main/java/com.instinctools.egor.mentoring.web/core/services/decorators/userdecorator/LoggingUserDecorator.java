package com.instinctools.egor.mentoring.web.core.services.decorators.userdecorator;

import com.instinctools.egor.mentoring.web.core.entity.User;
import com.instinctools.egor.mentoring.web.core.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class LoggingUserDecorator implements UserService {
    private static final Logger log = LoggerFactory.getLogger(LoggingUserDecorator.class);
    private UserService wrappee;

    public LoggingUserDecorator(UserService wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public void createUser(User user) {
        wrappee.createUser(user);
        log.info("User: " + user.toString() + "\nCreated");
    }

    @Override
    public void deleteUser(User user) {
        wrappee.deleteUser(user);
        log.info("User: " + user.toString() + "\nDeleted");
    }

    @Override
    public void updateUser(User user) {
        wrappee.updateUser(user);
        log.info("User with id: " + user.getId() + " was updated\n" +
                "Result: " + user.toString());
    }

    @Override
    public User getUserById(String id) {
        log.info("You look for user with id: " + id + "\n");
        User userById = wrappee.getUserById(id);
        log.info("Found user: " + userById.toString());
        return userById;
    }

    @Override
    public List<User> getAllUsers() {
        log.info("You look for list of all users");
        return wrappee.getAllUsers();
    }
}
