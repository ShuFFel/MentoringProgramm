package com.instinctools.egor.mentoring.noSQL.core.repository;

import com.instinctools.egor.mentoring.noSQL.core.entity.Book;

import java.util.List;

public interface BookRepository {
    void createBook(Book book);

    void updateBook(Book book);

    void deleteBook(Book book);

    List<Book> getBooksByOwnerId(String id);

    List<Book> getAllBooks();
}
