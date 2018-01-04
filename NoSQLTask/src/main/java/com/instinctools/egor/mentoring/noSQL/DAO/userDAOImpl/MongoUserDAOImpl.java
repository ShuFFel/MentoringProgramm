package com.instinctools.egor.mentoring.noSQL.DAO.userDAOImpl;

import com.instinctools.egor.mentoring.noSQL.DAO.DAOUtil.DAOUtil;
import com.instinctools.egor.mentoring.noSQL.DAO.UserDAO;
import com.instinctools.egor.mentoring.noSQL.entity.MongoEntity.UserMongoEntity;
import com.instinctools.egor.mentoring.noSQL.entity.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import java.util.ArrayList;
import java.util.List;

public class MongoUserDAOImpl implements UserDAO {

    private static DBCollection collection = DAOUtil.getMongoDB().getCollection("user");


    @Override
    public void createUser(User user) {
        UserMongoEntity userToSave = UserMongoEntity.fromUser(user);
        collection.save(userToSave.toDbObject());
    }

    @Override
    public void deleteUser(User user) {
        UserMongoEntity userToDelete = UserMongoEntity.fromUser(user);
        collection.remove(userToDelete.toDbObject());
    }

    @Override
    public User getUserById(String id) {
        BasicDBObject searchQuery = new BasicDBObject().append("_id", id);
        DBObject object = collection.findOne(searchQuery);
        return UserMongoEntity.fromDBObject(object);
    }

    @Override
    public void updateUser(User user) {
        UserMongoEntity userToUpdate = UserMongoEntity.fromUser(user);
        BasicDBObject searchQuery = new BasicDBObject().append("_id", userToUpdate.get_id());
        collection.update(searchQuery, userToUpdate.toDbObject());
    }

    @Override
    public List<User> getAllUsers() {
        List<DBObject> list = collection.find().toArray();
        List<User> users = new ArrayList<>();
        list.forEach(object -> users.add(UserMongoEntity.fromDBObject(object)));
        return users;
    }
}
