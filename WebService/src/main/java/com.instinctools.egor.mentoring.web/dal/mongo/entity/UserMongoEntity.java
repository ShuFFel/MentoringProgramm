package com.instinctools.egor.mentoring.web.dal.mongo.entity;

import com.instinctools.egor.mentoring.web.core.entity.Book;
import com.instinctools.egor.mentoring.web.core.entity.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.util.Date;
import java.util.UUID;

public class UserMongoEntity {
    private String _id;
    private String userName;
    private Date dateOfBirth;

    public UserMongoEntity() {
        this._id = UUID.randomUUID().toString();
    }

    public UserMongoEntity(final String userName, final Date dateOfBirth) {
        this._id = UUID.randomUUID().toString();
        this.dateOfBirth = new Date(dateOfBirth.getTime());
        this.userName = userName;
    }

    public void addBook(final Book book) {
        book.setOwner(this.toUser());
    }

    public void removeBook(final Book book) {
        book.setOwner(null);
    }

    public String getId() {
        return _id;
    }


    public Date getDateOfBirth() {
        return new Date(this.dateOfBirth.getTime());
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public void setDateOfBirth(final Date dateOfBirth) {
        this.dateOfBirth = new Date(dateOfBirth.getTime());
    }

    public void setId(final String id) {
        this._id = id;
    }

    public static UserMongoEntity fromDBObject(final DBObject object) {
        UserMongoEntity user = new UserMongoEntity();
        user._id = (String) object.get("_id");
        user.userName = (String) object.get("userName");
        user.dateOfBirth = (Date) object.get("birth_date");
        return user;
    }

    public DBObject toDbObject() {
        DBObject dbObject = new BasicDBObject();
        dbObject.put("_id", _id);
        dbObject.put("userName", userName);
        dbObject.put("birth_date", dateOfBirth);
        return dbObject;
    }

    public static UserMongoEntity fromUser(final User user) {
        UserMongoEntity userMongoEntity = new UserMongoEntity();
        userMongoEntity._id = user.getId();
        userMongoEntity.dateOfBirth = user.getDateOfBirth();
        userMongoEntity.userName = user.getUserName();
        return userMongoEntity;
    }

    public User toUser() {
        User user = new User(userName, dateOfBirth);
        user.setId(_id);
        return user;
    }
}
