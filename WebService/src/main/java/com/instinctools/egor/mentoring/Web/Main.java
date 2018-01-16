package com.instinctools.egor.mentoring.Web;

import com.instinctools.egor.mentoring.Web.core.factory.EntityFactory;
import com.instinctools.egor.mentoring.Web.core.repository.BookRepository;
import com.instinctools.egor.mentoring.Web.core.repository.UserRepository;
import com.instinctools.egor.mentoring.Web.core.services.BookService;
import com.instinctools.egor.mentoring.Web.core.services.UserService;
import com.instinctools.egor.mentoring.Web.core.services.serviceimpl.BookServiceImpl;
import com.instinctools.egor.mentoring.Web.core.services.serviceimpl.UserServiceImpl;
import com.instinctools.egor.mentoring.Web.dal.mongo.dao.MongoBookDAO;
import com.instinctools.egor.mentoring.Web.dal.mongo.dao.MongoUserDAO;
import com.instinctools.egor.mentoring.Web.dal.mongo.factory.MongoEntityFactory;
import com.instinctools.egor.mentoring.Web.dal.mysql.dao.MySQLBookDAO;
import com.instinctools.egor.mentoring.Web.dal.mysql.dao.MySQLUserDAO;
import com.instinctools.egor.mentoring.Web.dal.mysql.factory.MySQLEntityFactory;
import com.instinctools.egor.mentoring.Web.web.soap.BookServiceSOAPImpl;
import com.instinctools.egor.mentoring.Web.web.soap.UserServiceSOAPImpl;
import com.mongodb.DB;
import com.mongodb.MongoClient;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.xml.ws.Endpoint;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Chose type of storage(SQl by default):\n" + "1 - MySQL\n" + "2 - MongoDB\n");
        Integer chose = scanner.nextInt();
        UserRepository userRepository = null;
        BookRepository bookRepository = null;
        EntityFactory factory;
        if (chose == 2) {
            try {
                DB mongoDB = new MongoClient("localhost", 27017).getDB("test");
                userRepository = new MongoUserDAO(mongoDB);
                bookRepository = new MongoBookDAO(mongoDB);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            factory = new MongoEntityFactory();
        } else {
            EntityManager manager = Persistence.createEntityManagerFactory("my-perst").createEntityManager();
            userRepository = new MySQLUserDAO(manager);
            bookRepository = new MySQLBookDAO(manager);
            factory = new MySQLEntityFactory();
        }
        UserService userService = new UserServiceImpl(userRepository, bookRepository);
        BookService bookService = new BookServiceImpl(userRepository, bookRepository);
        Endpoint.publish("http://localhost:8001/UserService", new UserServiceSOAPImpl(factory, userService, bookService));
        Endpoint.publish("http://localhost:8002/BookService", new BookServiceSOAPImpl(bookService, userService, factory));
        System.out.println("success");
    }
}
