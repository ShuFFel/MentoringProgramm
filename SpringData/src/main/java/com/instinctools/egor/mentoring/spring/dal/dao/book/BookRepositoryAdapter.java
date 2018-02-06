package com.instinctools.egor.mentoring.spring.dal.dao.book;

import com.instinctools.egor.mentoring.spring.core.entity.Book;
import com.instinctools.egor.mentoring.spring.core.repository.BookRepository;
import com.instinctools.egor.mentoring.spring.dal.entities.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("bookDAO")
public class BookRepositoryAdapter implements BookRepository {

    private final BookDao bookDao;

    @Autowired
    public BookRepositoryAdapter(final BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void createBook(final Book book) {
        BookEntity bookEntity = BookEntity.fromBook(book);
        bookDao.save(bookEntity);
    }

    @Override
    public void updateBook(final Book book) {
        BookEntity bookEntity = BookEntity.fromBook(book);
        bookDao.save(bookEntity);
    }

    @Override
    public void deleteBook(final String bookId) {
        bookDao.delete(bookId);
    }

    @Override
    public List<Book> getBooksByOwnerId(final String id) {
        List<Book> resultList = new ArrayList<>();
        bookDao.findByOwnerId(id).forEach(bookEntity -> resultList.add(bookEntity.toBook()));
        return resultList;
    }

    @Override
    public Book getBookById(final String id) {
        return bookDao.findOne(id).toBook();
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> resultList = new ArrayList<>();
        bookDao.findAll().forEach(bookEntity -> resultList.add(bookEntity.toBook()));
        return resultList;
    }
}
