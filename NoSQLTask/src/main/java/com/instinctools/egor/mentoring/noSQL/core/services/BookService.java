package com.instinctools.egor.mentoring.noSQL.core.services;

import com.instinctools.egor.mentoring.noSQL.core.entity.Book;
import com.instinctools.egor.mentoring.noSQL.core.entity.User;

import java.util.List;

public interface BookService {
    void createBook(Book book);

    void deleteBook(Book book);

    void updateBook(Book book);

    void assignBook(User user, Book book);

    List<Book> getAllBooks();
}
