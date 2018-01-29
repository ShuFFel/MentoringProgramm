package com.instinctools.egor.mentoring.spring.dal.dao;

import com.instinctools.egor.mentoring.spring.core.entity.User;
import com.instinctools.egor.mentoring.spring.core.repository.UserRepository;
import com.instinctools.egor.mentoring.spring.dal.entities.UserEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao implements UserRepository {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @Override
    public void createUser(User user) {
        UserEntity userEntity = UserEntity.fromUser(user);
        manager.persist(userEntity);
        user.setId(userEntity.getId());
    }

    @Transactional
    @Override
    public void deleteUser(User user) {
        UserEntity entityToDelete = manager.find(UserEntity.class, user.getId());
        manager.remove(entityToDelete);
    }

    @Transactional
    @Override
    public User getUserById(String id) {
        return manager.find(UserEntity.class, id).toUser();
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        manager.merge(UserEntity.fromUser(user));
    }

    @Transactional
    @Override
    public List<User> getAllUsers() {
        List<UserEntity> resultList;
        resultList = manager.createQuery("from User", UserEntity.class).getResultList();
        List<User> list = new ArrayList<>();
        resultList.forEach(userSQLEntity -> list.add(userSQLEntity.toUser()));
        return list;
    }
}
