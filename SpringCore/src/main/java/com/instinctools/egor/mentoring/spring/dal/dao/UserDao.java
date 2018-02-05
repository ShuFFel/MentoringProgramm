package com.instinctools.egor.mentoring.spring.dal.dao;

import com.instinctools.egor.mentoring.spring.core.entity.User;
import com.instinctools.egor.mentoring.spring.core.repository.UserRepository;
import com.instinctools.egor.mentoring.spring.dal.entities.UserEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository("userDAO")
public class UserDao implements UserRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void createUser(final User user) {
        UserEntity userEntity = UserEntity.fromUser(user);
        manager.persist(userEntity);
        user.setId(userEntity.getId());
    }

    @Override
    public void deleteUser(final User user) {
        UserEntity entityToDelete = manager.find(UserEntity.class, user.getId());
        manager.remove(entityToDelete);
    }

    @Override
    public User getUserById(final String id) {
        return manager.find(UserEntity.class, id).toUser();
    }

    @Override
    public void updateUser(final User user) {
        manager.merge(UserEntity.fromUser(user));
    }

    @Override
    public List<User> getAllUsers() {
        List<UserEntity> resultList;
        resultList = manager.createQuery("from User", UserEntity.class).getResultList();
        List<User> list = new ArrayList<>();
        resultList.forEach(userSQLEntity -> list.add(userSQLEntity.toUser()));
        return list;
    }
}
