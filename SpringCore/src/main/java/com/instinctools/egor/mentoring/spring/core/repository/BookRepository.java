package com.instinctools.egor.mentoring.spring.core.repository;

import com.instinctools.egor.mentoring.spring.core.entity.Book;

import java.util.List;

public interface BookRepository {
    void createBook(Book book);

    void updateBook(Book book);

    void deleteBook(String bookId);

    List<Book> getBooksByOwnerId(String id);

    Book getBookById(String id);

    List<Book> getAllBooks();
}
