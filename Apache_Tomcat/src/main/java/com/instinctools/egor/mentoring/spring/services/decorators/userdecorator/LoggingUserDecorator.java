package com.instinctools.egor.mentoring.spring.services.decorators.userdecorator;

import com.instinctools.egor.mentoring.spring.core.entity.User;
import com.instinctools.egor.mentoring.spring.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("loggingUserDecorator")
public class LoggingUserDecorator implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingUserDecorator.class);
    private UserService wrappee;

    public LoggingUserDecorator(@Qualifier("userService") final UserService wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public void createUser(final User user) {
        wrappee.createUser(user);
        LOGGER.info("User: " + user.toString() + "\nCreated");
    }

    @Override
    public void deleteUser(final User user) {
        wrappee.deleteUser(user);
        LOGGER.info("User: " + user.toString() + "\nDeleted");
    }

    @Override
    public void updateUser(final User user) {
        wrappee.updateUser(user);
        LOGGER.info("User with id: " + user.getId() + " was updated\n" +
                        "Result: " + user.toString());
    }

    @Override
    public User getUserById(final String id) {
        LOGGER.info("You look for user with id: " + id + "\n");
        User userById = wrappee.getUserById(id);
        LOGGER.info("Found user: " + userById.toString());
        return userById;
    }

    @Override
    public List<User> getAllUsers() {
        LOGGER.info("You look for list of all users");
        return wrappee.getAllUsers();
    }
}
