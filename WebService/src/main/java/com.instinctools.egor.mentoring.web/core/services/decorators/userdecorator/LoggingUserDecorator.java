package com.instinctools.egor.mentoring.web.core.services.decorators.userdecorator;

import com.instinctools.egor.mentoring.web.core.entity.User;
import com.instinctools.egor.mentoring.web.core.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class LoggingUserDecorator extends BaseUserDecorator {
    private static final Logger log = LogManager.getLogger();

    public LoggingUserDecorator(UserService service) {
        super(service);
    }

    @Override
    public void createUser(User user) {
        super.createUser(user);
        log.info("User: " + user.toString() + "\nCreated");
    }

    @Override
    public void deleteUser(User user) {
        super.deleteUser(user);
        log.info("User: " + user.toString() + "\nDeleted");
    }

    @Override
    public void updateUser(User user) {
        super.updateUser(user);
        log.info("User with id: " + user.getId() + " was updated\n" +
                "Result: " + user.toString());
    }

    @Override
    public User getUserById(String id) {
        log.info("You look for user with id: " + id + "\n");
        User userById = super.getUserById(id);
        log.info("Found user: " + userById.toString());
        return userById;
    }

    @Override
    public List<User> getAllUsers() {
        log.info("You look for list of all users");
        return super.getAllUsers();
    }
}
