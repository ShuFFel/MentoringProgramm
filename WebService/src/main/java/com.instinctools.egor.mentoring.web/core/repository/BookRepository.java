package com.instinctools.egor.mentoring.web.core.repository;

import com.instinctools.egor.mentoring.web.core.entity.Book;

import java.util.List;

public interface BookRepository {
    void createBook(Book book);

    void updateBook(Book book);

    void deleteBook(Book book);

    List<Book> getBooksByOwnerId(String id);

    Book getBookById(String id);

    List<Book> getAllBooks();
}
