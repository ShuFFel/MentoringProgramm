package com.instinctools.egor.mentoring.noSQL;

import com.instinctools.egor.mentoring.noSQL.DAO.BookDAO;
import com.instinctools.egor.mentoring.noSQL.DAO.UserDAO;
import com.instinctools.egor.mentoring.noSQL.DAO.bookDAOImpl.MongoBookDAOImpl;
import com.instinctools.egor.mentoring.noSQL.DAO.bookDAOImpl.MySQLBookDAOImpl;
import com.instinctools.egor.mentoring.noSQL.DAO.userDAOImpl.MongoUserDAOImpl;
import com.instinctools.egor.mentoring.noSQL.DAO.userDAOImpl.MySQLUserDAOImpl;
import com.instinctools.egor.mentoring.noSQL.commandlinemenu.BookWorkingMenu;
import com.instinctools.egor.mentoring.noSQL.commandlinemenu.UserWorkingMenu;
import com.instinctools.egor.mentoring.noSQL.entity.Book;
import com.instinctools.egor.mentoring.noSQL.entity.User;
import com.instinctools.egor.mentoring.noSQL.factory.EntityFactory;
import com.instinctools.egor.mentoring.noSQL.factory.MongoEntityFactory;
import com.instinctools.egor.mentoring.noSQL.factory.MySQLEntityFactory;
import com.instinctools.egor.mentoring.noSQL.service.BookService;
import com.instinctools.egor.mentoring.noSQL.service.UserService;
import com.instinctools.egor.mentoring.noSQL.service.serviceImpl.BookServiceImpl;
import com.instinctools.egor.mentoring.noSQL.service.serviceImpl.UserServiceImpl;

import java.util.Scanner;

public class Main {

    private static Scanner scanner;

    public static void main(String[] args) {
        /*UserDAO userDAO = new MongoUserDAOImpl();
        BookDAO bookDAO = new MongoBookDAOImpl();
        UserService userService = new UserServiceImpl(userDAO, bookDAO);
        BookService bookService = new BookServiceImpl(userDAO, bookDAO);
        Book bok1 = new Book();
        User user = userService.getAllUsers().get(0);
        Book book = bookService.getAllBooks().get(0);
        user.setUser_name("user1");
        user.addBook(bok1);
        userService.updateUser(user);
        book.setName("book1");
        bookService.updateBook(book);
        bookService.assignBook(user, book);*/
        scanner = new Scanner(System.in);
        System.out.println("Chose type of storage(SQl by default):\n" +
                "1 - MySQL\n" +
                "2 - MongoDB\n");
        Integer chose = scanner.nextInt();
        UserDAO userDAO;
        BookDAO bookDAO;
        EntityFactory factory;
        if(chose == 2){
            userDAO = new MongoUserDAOImpl();
            bookDAO = new MongoBookDAOImpl();
            factory = new MongoEntityFactory();
        }
        else{
            userDAO = new MySQLUserDAOImpl();
            bookDAO = new MySQLBookDAOImpl();
            factory = new MySQLEntityFactory();
        }
        UserService userService = new UserServiceImpl(userDAO, bookDAO);
        BookService bookService = new BookServiceImpl(userDAO, bookDAO);
        BookWorkingMenu bookWorkingMenu = new BookWorkingMenu(bookService, factory);
        UserWorkingMenu userWorkingMenu = new UserWorkingMenu(userService, bookWorkingMenu, factory);
        userWorkingMenu.start();
        System.exit(0);
    }
}
