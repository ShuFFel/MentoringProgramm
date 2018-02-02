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

@Repository("bookDAO")
public class BookDao implements BookRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public void createBook(final Book book) {
        BookEntity bookEntity = BookEntity.fromBook(book);
        manager.persist(bookEntity);
        book.setId(bookEntity.getId());
    }

    @Override
    public void updateBook(final Book book) {
        manager.merge(BookEntity.fromBook(book));
    }

    @Override
    public void deleteBook(final String bookId) {
        BookEntity bookToDelete = manager.find(BookEntity.class, bookId);
        manager.remove(bookToDelete);
    }

    @Override
    public List<Book> getBooksByOwnerId(final String id) {
        List<BookEntity> sqlEntities;
        sqlEntities = manager.createQuery("from Book where owner.id= :id").setParameter("id", id).getResultList();
        List<Book> bookToReturn = new ArrayList<>();
        sqlEntities.forEach(bookSQLEntity -> bookToReturn.add(bookSQLEntity.toBook()));
        return bookToReturn;
    }

    @Override
    public Book getBookById(final String id) {
        BookEntity bookEntity = manager.find(BookEntity.class, id);
        return (bookEntity != null) ? bookEntity.toBook() : null;
    }

    @Override
    public List<Book> getAllBooks() {
        List<BookEntity> bookList;
        bookList = manager.createQuery("from Book", BookEntity.class).getResultList();
        List<Book> bookToReturn = new ArrayList<>();
        bookList.forEach(bookEntity -> bookToReturn.add(bookEntity.toBook()));
        return bookToReturn;
    }
}
