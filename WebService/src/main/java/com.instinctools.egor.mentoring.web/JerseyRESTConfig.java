package com.instinctools.egor.mentoring.web;

import com.instinctools.egor.mentoring.web.core.factory.ServiceFactory;
import com.instinctools.egor.mentoring.web.core.services.BookService;
import com.instinctools.egor.mentoring.web.core.services.UserService;
import com.instinctools.egor.mentoring.web.core.services.serviceimpl.BookServiceImpl;
import com.instinctools.egor.mentoring.web.core.services.serviceimpl.UserServiceImpl;
import com.instinctools.egor.mentoring.web.dal.factory.ServiceFactoryImpl;
import com.instinctools.egor.mentoring.web.factories.SettingService;
import com.instinctools.egor.mentoring.web.factories.StorageType;
import com.instinctools.egor.mentoring.web.web.rest.BookRESTController;
import com.instinctools.egor.mentoring.web.web.rest.SettingRESTController;
import com.instinctools.egor.mentoring.web.web.rest.UserRESTController;
import com.mongodb.DB;

import javax.persistence.EntityManager;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@ApplicationPath("/")
public class JerseyRESTConfig extends Application {

    private BookRESTController bookServiceREST;
    private UserRESTController userServiceREST;
    private SettingRESTController settings;

    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> h = new HashSet<>();
        h.add(BookRESTController.class);
        h.add(UserRESTController.class);
        h.add(SettingRESTController.class);
        return h;
    }

    @Override
    public Set<Object> getSingletons() {
        HashSet<Object> h = new HashSet<>();
        configurate();
        h.add(bookServiceREST);
        h.add(userServiceREST);
        h.add(settings);
        return h;
    }

    private void configurate() {
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
        bookServiceREST = new BookRESTController(bookService, userService);
        userServiceREST = new UserRESTController(userService);
        settings = new SettingRESTController(settingService);
    }
}
