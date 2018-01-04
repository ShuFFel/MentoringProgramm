package com.instinctools.egor.mentoring.noSQL.entity.MongoEntity;

import com.instinctools.egor.mentoring.noSQL.entity.Book;
import com.instinctools.egor.mentoring.noSQL.entity.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.util.UUID;

public class BookMongoEntity implements Book {
    private String _id;
    private String name;
    private String author;
    private User owner;

    public BookMongoEntity() {
        this._id = UUID.randomUUID().toString();
    }

    public BookMongoEntity(String name, String author) {
        this._id = UUID.randomUUID().toString();
        this.name = name;
        this.author = author;
    }

    @Override
    public User getOwner() {
        return owner;
    }

    @Override
    public void setOwner(User user) {
        this.owner = user;
    }

    @Override
    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String get_id() {
        return _id;
    }

    @Override
    public String getAuthor() {
        return this.author;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public static BookMongoEntity fromDBObject(DBObject object) {
        BookMongoEntity book = new BookMongoEntity();
        book._id = (String) object.get("_id");
        book.name = (String) object.get("name");
        book.author = (String) object.get("author");
        book.owner = (object.get("owner") != null) ? UserMongoEntity.fromDBObject((DBObject) object.get("owner")) : null;
        return book;
    }

    public DBObject toDBObject() {
        DBObject dbObject = new BasicDBObject();
        dbObject.put("_id", _id);
        dbObject.put("name", name);
        dbObject.put("author", author);
        dbObject.put("owner", (owner != null) ? UserMongoEntity.fromUser(owner).toDbObject() : null);
        return dbObject;
    }

    public static BookMongoEntity fromBook(Book book) {
        BookMongoEntity bookMongoEntity = new BookMongoEntity();
        bookMongoEntity._id = book.get_id();
        bookMongoEntity.author = book.getAuthor();
        bookMongoEntity.name = book.getName();
        bookMongoEntity.owner = book.getOwner();
        return bookMongoEntity;
    }

    @Override
    public String toString() {
        return String.format("id: %s \nname: %s\nauthor: %s\nowner: %s", _id, name, author, (owner != null) ? owner.get_id() : owner);
    }
}
