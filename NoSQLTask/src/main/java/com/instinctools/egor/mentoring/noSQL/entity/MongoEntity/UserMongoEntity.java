package com.instinctools.egor.mentoring.noSQL.entity.MongoEntity;

import com.instinctools.egor.mentoring.noSQL.entity.Book;
import com.instinctools.egor.mentoring.noSQL.entity.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.util.Date;
import java.util.UUID;

public class UserMongoEntity implements User {
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

    @Override
    public void addBook(Book book) {
        book.setOwner(this);
    }

    @Override
    public void removeBook(Book book) {
        book.setOwner(null);
    }

    @Override
    public String get_id() {
        return _id;
    }

    @Override
    public Date getDate_of_birth() {
        return this.date_of_birth;
    }

    @Override
    public String getUser_name() {
        return this.user_name;
    }

    @Override
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    @Override
    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public void set_id(String _id) {
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

    public static UserMongoEntity fromUser(User user){
        UserMongoEntity userMongoEntity = new UserMongoEntity();
        userMongoEntity._id = user.get_id();
        userMongoEntity.date_of_birth = user.getDate_of_birth();
        userMongoEntity.user_name = user.getUser_name();
        return userMongoEntity;
    }

    @Override
    public String toString() {
        return String.format("name: %s \ndate_of_birth: %s \n", user_name, date_of_birth);
    }
}
