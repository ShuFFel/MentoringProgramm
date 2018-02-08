package com.instinctools.egor.mentoring.spring.dal.dao.user;

import com.instinctools.egor.mentoring.spring.core.entity.User;
import com.instinctools.egor.mentoring.spring.core.repository.UserRepository;
import com.instinctools.egor.mentoring.spring.dal.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userRepo")
public class UserRepositoryAdapter implements UserRepository {

    private final UserDao userDao;

    @Autowired
    public UserRepositoryAdapter(final UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void createUser(final User user) {
        userDao.save(UserEntity.fromUser(user));
    }

    @Override
    public void deleteUser(final User user) {
        userDao.delete(UserEntity.fromUser(user));
    }

    @Override
    public User getUserById(final String id) {
        return userDao.findOne(id).toUser();
    }

    @Override
    public void updateUser(final User user) {
        userDao.save(UserEntity.fromUser(user));
    }

    @Override
    public List<User> getAllUsers() {
        List<User> resulList = new ArrayList<>();
        userDao.findAll().forEach(userEntity -> resulList.add(userEntity.toUser()));
        return resulList;
    }
}
