package com.instinctools.egor.mentoring.noSQL.DAO;

import com.instinctools.egor.mentoring.noSQL.entity.User;

import java.util.List;

public interface UserDAO {
    void createUser(User user);
    void deleteUser(User user);
    User getUserById(String id);
    void updateUser(User user);
    List<User> getAllUsers();
}
