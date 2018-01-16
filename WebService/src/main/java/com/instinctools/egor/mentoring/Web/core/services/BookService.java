package com.instinctools.egor.mentoring.Web.core.services;

import com.instinctools.egor.mentoring.Web.core.entity.Book;
import com.instinctools.egor.mentoring.Web.core.entity.User;

import java.util.List;

public interface BookService {
    void createBook(Book book);

    void deleteBook(Book book);

    void updateBook(Book book);

    void assignBook(User user, Book book);

    Book getBookById(String id);

    List<Book> getAllBooks();
}
