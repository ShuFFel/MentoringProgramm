package com.instinctools.egor.mentoring.Web.dal.mongo.dao;

import com.instinctools.egor.mentoring.Web.core.entity.Book;
import com.instinctools.egor.mentoring.Web.core.repository.BookRepository;
import com.instinctools.egor.mentoring.Web.dal.mongo.entity.BookMongoEntity;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import java.util.ArrayList;
import java.util.List;

public class MongoBookDAO implements BookRepository {

    private final DBCollection collection;

    public MongoBookDAO(DB mongoDB) {
        this.collection = mongoDB.getCollection("book");
    }


    @Override
    public void createBook(Book book) {
        BookMongoEntity bookToSave = BookMongoEntity.fromBook(book);
        collection.save(bookToSave.toDBObject());
    }

    @Override
    public void updateBook(Book book) {
        BookMongoEntity bookToUpdate = BookMongoEntity.fromBook(book);
        BasicDBObject searchQuery = new BasicDBObject().append("_id", bookToUpdate.getId());
        collection.update(searchQuery, bookToUpdate.toDBObject());
    }

    @Override
    public void deleteBook(Book book) {
        BasicDBObject objectToDelete = new BasicDBObject().append("_id", book.getId());
        collection.remove(objectToDelete);
    }

    @Override
    public List<Book> getBooksByOwnerId(String id) {
        List<DBObject> list = collection.find().toArray();
        List<Book> bookList = new ArrayList<>();
        list.forEach(object -> {
            BookMongoEntity book = BookMongoEntity.fromDBObject(object);
            if (book.getOwner() != null && book.getOwner().getId().equals(id))
                bookList.add(book.toBook());
        });
        return bookList;
    }

    @Override
    public Book getBookById(String id) {
        BasicDBObject query = new BasicDBObject().append("_id", id);
        DBObject result = collection.findOne(query);
        return BookMongoEntity.fromDBObject(result).toBook();
    }

    @Override
    public List<Book> getAllBooks() {
        List<DBObject> list = collection.find().toArray();
        List<Book> bookList = new ArrayList<>();
        list.forEach(object -> bookList.add(BookMongoEntity.fromDBObject(object).toBook()));
        return bookList;
    }
}
