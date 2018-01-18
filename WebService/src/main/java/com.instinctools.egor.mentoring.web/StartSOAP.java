package com.instinctools.egor.mentoring.web;

import com.instinctools.egor.mentoring.web.core.factory.EntityFactory;
import com.instinctools.egor.mentoring.web.core.services.BookService;
import com.instinctools.egor.mentoring.web.core.services.UserService;
import com.instinctools.egor.mentoring.web.factories.GenericServices;
import com.instinctools.egor.mentoring.web.factories.StorageType;
import com.instinctools.egor.mentoring.web.web.soap.BookServiceSOAPImpl;
import com.instinctools.egor.mentoring.web.web.soap.UserServiceSOAPImpl;

import javax.xml.ws.Endpoint;
import java.util.Scanner;

public class StartSOAP {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Chose type of storage(SQl by default):\n" + "MySQL\n" + "Mongo\n");
        StorageType chose = StorageType.valueOf(scanner.next().toUpperCase());

        new GenericServices().invoke();
        EntityFactory factory = GenericServices.getFactory();
        UserService userService = GenericServices.getUserService(chose);
        BookService bookService = GenericServices.getBookService(chose);

        Endpoint.publish("http://localhost:8001/UserService", new UserServiceSOAPImpl(factory, userService, bookService));
        Endpoint.publish("http://localhost:8002/BookService", new BookServiceSOAPImpl(bookService, userService, factory));
        System.out.println("success");
    }


}
