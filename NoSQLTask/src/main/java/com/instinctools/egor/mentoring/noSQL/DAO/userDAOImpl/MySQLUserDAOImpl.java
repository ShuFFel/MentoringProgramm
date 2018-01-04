package com.instinctools.egor.mentoring.noSQL.DAO.userDAOImpl;

import com.instinctools.egor.mentoring.noSQL.DAO.DAOUtil.DAOUtil;
import com.instinctools.egor.mentoring.noSQL.DAO.UserDAO;
import com.instinctools.egor.mentoring.noSQL.entity.MySQLEntity.UserSQLEntity;
import com.instinctools.egor.mentoring.noSQL.entity.User;

import javax.persistence.EntityManager;
import java.util.List;

public class MySQLUserDAOImpl implements UserDAO {
    @Override
    public void createUser(User user) {
        EntityManager manager = DAOUtil.getSessionFactory().createEntityManager();
        try{
            manager.getTransaction().begin();
            manager.persist(user);
            manager.getTransaction().commit();
        }
        finally {
            manager.close();
        }
    }

    @Override
    public void deleteUser(User user) {
        EntityManager manager = DAOUtil.getSessionFactory().createEntityManager();
        User userToDelete = manager.find(UserSQLEntity.class, user.get_id());
        try {
            manager.getTransaction().begin();
            manager.remove(userToDelete);
            manager.getTransaction().commit();
        }
        finally {
            manager.close();
        }
    }

    @Override
    public User getUserById(String id) {
        EntityManager manager = DAOUtil.getSessionFactory().createEntityManager();
        User userToReturn;
        try{
            userToReturn = manager.find(User.class, id);
        }
        finally {
            manager.close();
        }
        return userToReturn;
    }

    @Override
    public void updateUser(User user) {
        EntityManager manager = DAOUtil.getSessionFactory().createEntityManager();
        try{
            manager.getTransaction().begin();
            manager.merge(user);
            manager.getTransaction().commit();
        }
        finally {
            manager.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        EntityManager manager = DAOUtil.getSessionFactory().createEntityManager();
        List<User> resultList;
        try{
            manager.getTransaction().begin();
            resultList = manager.createQuery("from User", User.class).getResultList();
            manager.getTransaction().commit();
        }
        finally {
            manager.close();
        }
        return resultList;
    }
}
