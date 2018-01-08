package com.instinctools.egor.mentoring.noSQL.dal.mongo.entity;

import com.instinctools.egor.mentoring.noSQL.core.entity.Book;
import com.instinctools.egor.mentoring.noSQL.core.entity.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.util.Date;
import java.util.UUID;

public class UserMongoEntity {
    private String _id;
    private String user_name;
    private Date date_of_birth;

    public UserMongoEntity() {
        this._id = UUID.randomUUID().toString();
    }

    public UserMongoEntity(String user_name, Date date_of_birth) {
        this._id = UUID.randomUUID().toString();
        this.date_of_birth = date_of_birth;
        this.user_name = user_name;
    }

    public void addBook(Book book) {
        book.setOwner(this.toUser());
    }

    public void removeBook(Book book) {
        book.setOwner(null);
    }

    public String getId() {
        return _id;
    }


    public Date getDateOfBirth() {
        return this.date_of_birth;
    }

    public String getUserName() {
        return this.user_name;
    }

    public void setUserName(String user_name) {
        this.user_name = user_name;
    }

    public void setDateOfBirth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public static UserMongoEntity fromDBObject(DBObject object) {
        UserMongoEntity user = new UserMongoEntity();
        user._id = (String) object.get("_id");
        user.user_name = (String) object.get("user_name");
        user.date_of_birth = (Date) object.get("birth_date");
        return user;
    }

    public DBObject toDbObject() {
        DBObject dbObject = new BasicDBObject();
        dbObject.put("_id", _id);
        dbObject.put("user_name", user_name);
        dbObject.put("birth_date", date_of_birth);
        return dbObject;
    }

    public static UserMongoEntity fromUser(User user) {
        UserMongoEntity userMongoEntity = new UserMongoEntity();
        userMongoEntity._id = user.getId();
        userMongoEntity.date_of_birth = user.getDateOfBirth();
        userMongoEntity.user_name = user.getUserName();
        return userMongoEntity;
    }

    public User toUser() {
        User user = new User();
        user.setUserName(user_name);
        user.setId(_id);
        user.setDateOfBirth(date_of_birth);
        return user;
    }
}
