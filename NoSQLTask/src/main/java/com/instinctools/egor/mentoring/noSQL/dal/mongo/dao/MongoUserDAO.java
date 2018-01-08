package com.instinctools.egor.mentoring.noSQL.dal.mongo.dao;

import com.instinctools.egor.mentoring.noSQL.core.repository.UserRepository;
import com.instinctools.egor.mentoring.noSQL.dal.mongo.entity.UserMongoEntity;
import com.instinctools.egor.mentoring.noSQL.core.entity.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import java.util.ArrayList;
import java.util.List;

public class MongoUserDAO implements UserRepository {

    private final DBCollection collection;

    public MongoUserDAO(DB mongoDB){
        this.collection = mongoDB.getCollection("user");
    }


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
        return UserMongoEntity.fromDBObject(object).toUser();
    }

    @Override
    public void updateUser(User user) {
        UserMongoEntity userToUpdate = UserMongoEntity.fromUser(user);
        BasicDBObject searchQuery = new BasicDBObject().append("_id", userToUpdate.getId());
        collection.update(searchQuery, userToUpdate.toDbObject());
    }

    @Override
    public List<User> getAllUsers() {
        List<DBObject> list = collection.find().toArray();
        List<User> users = new ArrayList<>();
        list.forEach(object -> users.add(UserMongoEntity.fromDBObject(object).toUser()));
        return users;
    }
}
