package com.instinctools.egor.mentoring.noSQL.DAO;

import com.instinctools.egor.mentoring.noSQL.entity.Book;

import java.util.List;

public interface BookDAO {
    void createBook(Book book);

    void updateBook(Book book);

    void deleteBook(Book book);

    List<Book> getBooksByOwnerId(String id);

    List<Book> getAllBooks();
}
