package com.instinctools.egor.mentoring.noSQL;

import com.instinctools.egor.mentoring.noSQL.core.repository.BookRepository;
import com.instinctools.egor.mentoring.noSQL.core.repository.UserRepository;
import com.instinctools.egor.mentoring.noSQL.dal.mongo.dao.MongoBookDAO;
import com.instinctools.egor.mentoring.noSQL.dal.mysql.dao.MySQLBookDAO;
import com.instinctools.egor.mentoring.noSQL.dal.mongo.dao.MongoUserDAO;
import com.instinctools.egor.mentoring.noSQL.dal.mysql.dao.MySQLUserDAO;
import com.instinctools.egor.mentoring.noSQL.ui.BookWorkingMenu;
import com.instinctools.egor.mentoring.noSQL.ui.UserWorkingMenu;
import com.instinctools.egor.mentoring.noSQL.core.factory.EntityFactory;
import com.instinctools.egor.mentoring.noSQL.dal.mongo.factory.MongoEntityFactory;
import com.instinctools.egor.mentoring.noSQL.dal.mysql.factory.MySQLEntityFactory;
import com.instinctools.egor.mentoring.noSQL.core.services.BookService;
import com.instinctools.egor.mentoring.noSQL.core.services.UserService;
import com.instinctools.egor.mentoring.noSQL.core.services.serviceimpl.BookServiceImpl;
import com.instinctools.egor.mentoring.noSQL.core.services.serviceimpl.UserServiceImpl;
import com.mongodb.DB;
import com.mongodb.MongoClient;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {

    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        System.out.println("Chose type of storage(SQl by default):\n" +
                "1 - MySQL\n" +
                "2 - MongoDB\n");
        Integer chose = scanner.nextInt();
        UserRepository userRepository = null;
        BookRepository bookRepository = null;
        EntityFactory factory;
        if(chose == 2){
            try {
                DB mongoDB = new MongoClient("localhost",27017).getDB("test");
                userRepository = new MongoUserDAO(mongoDB);
                bookRepository = new MongoBookDAO(mongoDB);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            factory = new MongoEntityFactory();
        }
        else{
            EntityManager manager = Persistence.createEntityManagerFactory("my-perst").createEntityManager();
            userRepository = new MySQLUserDAO(manager);
            bookRepository = new MySQLBookDAO(manager);
            factory = new MySQLEntityFactory();
        }
        UserService userService = new UserServiceImpl(userRepository, bookRepository);
        BookService bookService = new BookServiceImpl(userRepository, bookRepository);
        BookWorkingMenu bookWorkingMenu = new BookWorkingMenu(bookService, factory);
        UserWorkingMenu userWorkingMenu = new UserWorkingMenu(userService, bookWorkingMenu, factory);
        userWorkingMenu.start();
        System.exit(0);
    }
}
