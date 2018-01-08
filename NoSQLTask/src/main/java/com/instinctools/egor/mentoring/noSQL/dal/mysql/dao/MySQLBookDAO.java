package com.instinctools.egor.mentoring.noSQL.dal.mysql.dao;

import com.instinctools.egor.mentoring.noSQL.core.repository.BookRepository;
import com.instinctools.egor.mentoring.noSQL.core.entity.Book;
import com.instinctools.egor.mentoring.noSQL.dal.mysql.entity.BookSQLEntity;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class MySQLBookDAO implements BookRepository {

    EntityManager manager;

    public MySQLBookDAO(EntityManager manager){
        this.manager = manager;
    }


    @Override
    public void createBook(Book book) {
        BookSQLEntity bookToSave = BookSQLEntity.fromBook(book);
        manager.getTransaction().begin();
        manager.persist(bookToSave);
        manager.getTransaction().commit();
    }

    @Override
    public void updateBook(Book book) {
        BookSQLEntity bookToUpdate = BookSQLEntity.fromBook(book);
        manager.isOpen();
        manager.getTransaction().begin();
        manager.merge(bookToUpdate);
        manager.getTransaction().commit();

    }

    @Override
    public void deleteBook(Book book) {
        BookSQLEntity bookToDelete = manager.find(BookSQLEntity.class, book.getId());
        manager.getTransaction().begin();
        manager.remove(bookToDelete);
        manager.getTransaction().commit();

    }

    @Override
    public List<Book> getBooksByOwnerId(String id) {
        List<BookSQLEntity> sqlEntities;
        manager.getTransaction().begin();
        sqlEntities = manager.createQuery("from Books where owner._id = :id").setParameter("id", id).getResultList();
        manager.getTransaction().commit();
        List<Book> bookToReturn = new ArrayList<>();
        sqlEntities.forEach(bookSQLEntity -> bookToReturn.add(bookSQLEntity.toBook()));
        return bookToReturn;
    }

    @Override
    public List<Book> getAllBooks() {
        List<BookSQLEntity> bookList = null;
        manager.getTransaction().begin();
        bookList = manager.createQuery("from Books", BookSQLEntity.class).getResultList();
        manager.getTransaction().commit();
        List<Book> resultList = new ArrayList<>();
        bookList.forEach(bookSQLEntity -> resultList.add(bookSQLEntity.toBook()));
        return resultList;
    }
}
