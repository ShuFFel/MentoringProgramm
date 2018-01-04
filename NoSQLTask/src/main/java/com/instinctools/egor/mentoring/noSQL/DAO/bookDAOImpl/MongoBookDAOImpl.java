package com.instinctools.egor.mentoring.noSQL.DAO.bookDAOImpl;

import com.instinctools.egor.mentoring.noSQL.DAO.BookDAO;
import com.instinctools.egor.mentoring.noSQL.DAO.DAOUtil.DAOUtil;
import com.instinctools.egor.mentoring.noSQL.entity.Book;
import com.instinctools.egor.mentoring.noSQL.entity.MongoEntity.BookMongoEntity;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import java.util.ArrayList;
import java.util.List;

public class MongoBookDAOImpl implements BookDAO {

    private static final DBCollection collection = DAOUtil.getMongoDB().getCollection("book");


    @Override
    public void createBook(Book book) {
        BookMongoEntity bookToSave = BookMongoEntity.fromBook(book);
        collection.save(bookToSave.toDBObject());
    }

    @Override
    public void updateBook(Book book) {
        BookMongoEntity bookToUpdate = BookMongoEntity.fromBook(book);
        BasicDBObject searchQuery = new BasicDBObject().append("_id", bookToUpdate.get_id());
        collection.update(searchQuery, bookToUpdate.toDBObject());
    }

    @Override
    public void deleteBook(Book book) {
        BasicDBObject objectToDelete  = new BasicDBObject().append("_id", book.get_id());
        collection.remove(objectToDelete);
    }

    @Override
    public List<Book> getBooksByOwnerId(String id) {
        List<DBObject> list = collection.find().toArray();
        List<Book> bookList = new ArrayList<>();
        list.forEach(object -> {
            BookMongoEntity book = BookMongoEntity.fromDBObject(object);
            if(book.getOwner() != null && book.getOwner().get_id().equals(id))
                bookList.add(book);
        });
        return bookList;
    }

    @Override
    public List<Book> getAllBooks() {
        List<DBObject> list = collection.find().toArray();
        List<Book> bookList = new ArrayList<>();
        list.forEach(object -> bookList.add(BookMongoEntity.fromDBObject(object)));
        return bookList;
    }
}
