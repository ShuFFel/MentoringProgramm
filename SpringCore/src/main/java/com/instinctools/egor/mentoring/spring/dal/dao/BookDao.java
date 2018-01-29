package com.instinctools.egor.mentoring.spring.dal.dao;

import com.instinctools.egor.mentoring.spring.core.entity.Book;
import com.instinctools.egor.mentoring.spring.core.repository.BookRepository;
import com.instinctools.egor.mentoring.spring.dal.entities.BookEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDao implements BookRepository {
    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @Override
    public void createBook(Book book) {
        manager.persist(BookEntity.fromBook(book));
    }

    @Transactional
    @Override
    public void updateBook(Book book) {
        manager.merge(BookEntity.fromBook(book));
    }

    @Transactional
    @Override
    public void deleteBook(Book book) {
        BookEntity bookToDelete = manager.find(BookEntity.class, book.getId());
        manager.remove(bookToDelete);
    }

    @Transactional
    @Override
    public List<Book> getBooksByOwnerId(String id) {
        List<BookEntity> sqlEntities;
        sqlEntities = manager.createQuery("from Book").getResultList();
        List<Book> bookToReturn = new ArrayList<>();
        sqlEntities.forEach(bookSQLEntity -> bookToReturn.add(bookSQLEntity.toBook()));
        return bookToReturn;
    }

    @Transactional
    @Override
    public Book getBookById(String id) {
        return manager.find(BookEntity.class, id).toBook();
    }

    @Transactional
    @Override
    public List<Book> getAllBooks() {
        List<BookEntity> bookList;
        bookList = manager.createQuery("from Book", BookEntity.class).getResultList();
        List<Book> bookToReturn = new ArrayList<>();
        bookList.forEach(bookEntity -> bookToReturn.add(bookEntity.toBook()));
        return bookToReturn;
    }
}
