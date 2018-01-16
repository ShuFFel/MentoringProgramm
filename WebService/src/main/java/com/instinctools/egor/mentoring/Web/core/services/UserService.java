package com.instinctools.egor.mentoring.Web.core.services;

import com.instinctools.egor.mentoring.Web.core.entity.User;

import java.util.List;

public interface UserService {
    void createUser(User user);

    void deleteUser(User user);

    void updateUser(User user);

    User getUserById(String id);

    List<User> getAllUsers();
}
