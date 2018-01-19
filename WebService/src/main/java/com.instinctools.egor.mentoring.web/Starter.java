package com.instinctools.egor.mentoring.web;

import com.instinctools.egor.mentoring.web.core.factory.ServiceFactory;
import com.instinctools.egor.mentoring.web.core.services.BookService;
import com.instinctools.egor.mentoring.web.core.services.UserService;
import com.instinctools.egor.mentoring.web.core.services.serviceimpl.BookServiceImpl;
import com.instinctools.egor.mentoring.web.core.services.serviceimpl.UserServiceImpl;
import com.instinctools.egor.mentoring.web.dal.factory.ServiceFactoryImpl;
import com.instinctools.egor.mentoring.web.factories.SettingService;
import com.instinctools.egor.mentoring.web.factories.StorageType;
import com.instinctools.egor.mentoring.web.web.soap.BookServiceSOAPImpl;
import com.instinctools.egor.mentoring.web.web.soap.UserServiceSOAPImpl;
import com.mongodb.DB;

import javax.persistence.EntityManager;
import javax.xml.ws.Endpoint;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Objects;


public class Starter {

    public static void main(String[] args) {

        Configs configs = null;
        try {
            configs = new Configs("application.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
        StorageType type = Objects.requireNonNull(configs).getStorageType();
        DB mongoDB = null;
        try {
            mongoDB = configs.getMongoDB();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        EntityManager entityManager = configs.getEntityManager();
        SettingService settingService = new SettingService(type);
        ServiceFactory serviceFactory = new ServiceFactoryImpl(settingService, entityManager, mongoDB);
        UserService userService = new UserServiceImpl(serviceFactory);
        BookService bookService = new BookServiceImpl(serviceFactory);

        Endpoint.publish(configs.getUserSOAPAddress(), new UserServiceSOAPImpl(userService));
        Endpoint.publish(configs.getBookSOAPAddress(), new BookServiceSOAPImpl(bookService, userService));
    }


}
