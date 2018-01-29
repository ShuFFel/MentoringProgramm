package com.instinctools.egor.mentoring.web.dal.mysql.dao;

import com.instinctools.egor.mentoring.web.core.entity.User;
import com.instinctools.egor.mentoring.web.core.repository.UserRepository;
import com.instinctools.egor.mentoring.web.dal.mysql.entity.UserSQLEntity;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class MySQLUserDAO implements UserRepository {

    private final EntityManager manager;

    public MySQLUserDAO(final EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void createUser(final User user) {
        UserSQLEntity userToSave = UserSQLEntity.fromUser(user);
        manager.getTransaction().begin();
        manager.persist(userToSave);
        manager.getTransaction().commit();
    }

    @Override
    public void deleteUser(final User user) {
        UserSQLEntity userToDelete = manager.find(UserSQLEntity.class, user.getId());
        manager.getTransaction().begin();
        manager.remove(userToDelete);
        manager.getTransaction().commit();
    }

    @Override
    public User getUserById(final String id) {
        User userToReturn;
        userToReturn = manager.find(User.class, id);
        return userToReturn;
    }

    @Override
    public void updateUser(final User user) {
        UserSQLEntity userToUpdate = UserSQLEntity.fromUser(user);
        manager.getTransaction().begin();
        manager.merge(userToUpdate);
        manager.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {
        List<UserSQLEntity> resultList;
        manager.getTransaction().begin();
        resultList = manager.createQuery("from Users", UserSQLEntity.class).getResultList();
        manager.getTransaction().commit();
        List<User> list = new ArrayList<>();
        resultList.forEach(userSQLEntity -> list.add(userSQLEntity.toUser()));
        return list;
    }
}
