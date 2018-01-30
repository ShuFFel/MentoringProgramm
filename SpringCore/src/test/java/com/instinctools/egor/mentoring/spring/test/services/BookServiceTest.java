package com.instinctools.egor.mentoring.spring.test.services;

import com.instinctools.egor.mentoring.spring.config.AppConfig;
import com.instinctools.egor.mentoring.spring.core.entity.Book;
import com.instinctools.egor.mentoring.spring.core.entity.User;
import com.instinctools.egor.mentoring.spring.services.BookService;
import com.instinctools.egor.mentoring.spring.services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class BookServiceTest {


    @Autowired
    private UserService userService;
    private BookService bookService;

    @Autowired
    public void setBookService(@Qualifier("loggingBookDecorator") final BookService bookService) {
        this.bookService = bookService;
    }

    @Test
    public void createDeleteServiceTest() {
        Book book = new Book("aaa", "author");
        bookService.createBook(book);
        Assert.assertNotNull(bookService.getBookById(book.getId()));
        bookService.deleteBook(book);
        Assert.assertNull(bookService.getBookById(book.getId()));
    }

    @Test
    public void updateServiceTest() {
        Book book = new Book("aaa", "author");
        bookService.createBook(book);
        String oldname = book.getName();
        book.setName("newUserName");
        bookService.updateBook(book);
        Book bookFromDB = bookService.getBookById(book.getId());
        Assert.assertFalse(oldname.equals(bookFromDB.getName()));
        bookService.deleteBook(bookFromDB);
    }

    @Test
    public void assignServiceTest() {
        Book book = new Book("aaa", "bbb");
        User user = new User("user", new Date(System.currentTimeMillis()));
        bookService.createBook(book);
        userService.createUser(user);
        bookService.assignBook(user, book);
        book = bookService.getBookById(book.getId());
        Assert.assertNotNull(book.getOwner());
        userService.deleteUser(user);
        Assert.assertNull(bookService.getBookById(book.getId()).getOwner());
        bookService.deleteBook(book);
    }

}
