package com.instinctools.egor.mentoring.web.dal.mysql.dao;

import com.instinctools.egor.mentoring.web.core.entity.Book;
import com.instinctools.egor.mentoring.web.core.repository.BookRepository;
import com.instinctools.egor.mentoring.web.dal.mysql.entity.BookSQLEntity;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class MySQLBookDAO implements BookRepository {

    private EntityManager manager;

    public MySQLBookDAO(final EntityManager manager) {
        this.manager = manager;
    }


    @Override
    public void createBook(final Book book) {
        BookSQLEntity bookToSave = BookSQLEntity.fromBook(book);
        manager.getTransaction().begin();
        manager.persist(bookToSave);
        manager.getTransaction().commit();
    }

    @Override
    public void updateBook(final Book book) {
        BookSQLEntity bookToUpdate = BookSQLEntity.fromBook(book);
        manager.isOpen();
        manager.getTransaction().begin();
        manager.merge(bookToUpdate);
        manager.getTransaction().commit();

    }

    @Override
    public void deleteBook(final Book book) {
        BookSQLEntity bookToDelete = manager.find(BookSQLEntity.class, book.getId());
        manager.getTransaction().begin();
        manager.remove(bookToDelete);
        manager.getTransaction().commit();

    }

    @Override
    public List<Book> getBooksByOwnerId(final String id) {
        List<BookSQLEntity> sqlEntities;
        manager.getTransaction().begin();
        sqlEntities = manager.createQuery("from Books where owner.id = :id").setParameter("id", id).getResultList();
        manager.getTransaction().commit();
        List<Book> bookToReturn = new ArrayList<>();
        sqlEntities.forEach(bookSQLEntity -> bookToReturn.add(bookSQLEntity.toBook()));
        return bookToReturn;
    }

    @Override
    public Book getBookById(final String id) {
        BookSQLEntity bookToReturn;
        bookToReturn = manager.find(BookSQLEntity.class, id);
        return bookToReturn.toBook();
    }

    @Override
    public List<Book> getAllBooks() {
        List<BookSQLEntity> bookList;
        manager.getTransaction().begin();
        bookList = manager.createQuery("from Books", BookSQLEntity.class).getResultList();
        manager.getTransaction().commit();
        List<Book> resultList = new ArrayList<>();
        bookList.forEach(bookSQLEntity -> resultList.add(bookSQLEntity.toBook()));
        return resultList;
    }
}
