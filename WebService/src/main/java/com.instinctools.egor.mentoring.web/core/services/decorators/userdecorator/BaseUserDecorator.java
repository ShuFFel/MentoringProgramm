package com.instinctools.egor.mentoring.web.core.services.decorators.userdecorator;

import com.instinctools.egor.mentoring.web.core.entity.User;
import com.instinctools.egor.mentoring.web.core.services.UserService;

import java.util.List;

public class BaseUserDecorator implements UserService {
    private UserService wrappee;

    public BaseUserDecorator(UserService service) {
        this.wrappee = service;
    }

    @Override
    public void createUser(User user) {
        wrappee.createUser(user);
    }

    @Override
    public void deleteUser(User user) {
        wrappee.deleteUser(user);
    }

    @Override
    public void updateUser(User user) {
        wrappee.updateUser(user);
    }

    @Override
    public User getUserById(String id) {
        return wrappee.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return wrappee.getAllUsers();
    }
}
