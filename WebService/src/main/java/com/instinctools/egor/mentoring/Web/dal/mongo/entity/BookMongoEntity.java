package com.instinctools.egor.mentoring.Web.dal.mongo.entity;

import com.instinctools.egor.mentoring.Web.core.entity.Book;
import com.instinctools.egor.mentoring.Web.core.entity.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.util.UUID;

public class BookMongoEntity {
    private String _id;
    private String name;
    private String author;
    private UserMongoEntity owner;

    public BookMongoEntity() {
        this._id = UUID.randomUUID().toString();
    }

    public BookMongoEntity(String name, String author) {
        this._id = UUID.randomUUID().toString();
        this.name = name;
        this.author = author;
    }

    public UserMongoEntity getOwner() {
        return owner;
    }

    public void setOwner(User user) {
        this.owner = UserMongoEntity.fromUser(user);
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return _id;
    }

    public String getAuthor() {
        return this.author;
    }

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
        dbObject.put("owner", (owner != null) ? owner.toDbObject() : null);
        return dbObject;
    }

    public static BookMongoEntity fromBook(Book book) {
        BookMongoEntity bookMongoEntity = new BookMongoEntity();
        bookMongoEntity._id = book.getId();
        bookMongoEntity.author = book.getAuthor();
        bookMongoEntity.name = book.getName();
        bookMongoEntity.owner = (book.getOwner() != null) ? UserMongoEntity.fromUser(book.getOwner()) : null;
        return bookMongoEntity;
    }

    public Book toBook() {
        Book book = new Book();
        book.setAuthor(author);
        book.setName(name);
        book.setOwner((owner != null) ? owner.toUser() : null);
        book.setId(_id);
        return book;
    }

    @Override
    public String toString() {
        return String.format("id: %s \nname: %s\nauthor: %s\nowner: %s", _id, name, author, owner);
    }
}
