package com.instinctools.egor.mentoring.web.core.repository;

import com.instinctools.egor.mentoring.web.core.entity.User;

import java.util.List;

public interface UserRepository {
    void createUser(User user);
    void deleteUser(User user);
    User getUserById(String id);
    void updateUser(User user);
    List<User> getAllUsers();
}
