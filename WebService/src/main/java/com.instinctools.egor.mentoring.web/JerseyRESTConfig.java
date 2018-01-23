package com.instinctools.egor.mentoring.web;

import com.instinctools.egor.mentoring.web.core.factory.RepositoryFactory;
import com.instinctools.egor.mentoring.web.core.services.BookService;
import com.instinctools.egor.mentoring.web.core.services.UserService;
import com.instinctools.egor.mentoring.web.core.services.decorators.bookdecorator.LoggingBookDecorator;
import com.instinctools.egor.mentoring.web.core.services.decorators.userdecorator.LoggingUserDecorator;
import com.instinctools.egor.mentoring.web.core.services.serviceimpl.BookServiceImpl;
import com.instinctools.egor.mentoring.web.core.services.serviceimpl.UserServiceImpl;
import com.instinctools.egor.mentoring.web.dal.factory.RepositoryFactoryImpl;
import com.instinctools.egor.mentoring.web.factories.SettingService;
import com.instinctools.egor.mentoring.web.factories.StorageType;
import com.instinctools.egor.mentoring.web.web.rest.BookRESTController;
import com.instinctools.egor.mentoring.web.web.rest.SettingRESTController;
import com.instinctools.egor.mentoring.web.web.rest.UserRESTController;
import com.mongodb.DB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private static final Logger log = LogManager.getLogger();
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
            log.error(e.getMessage());
        }
        StorageType type = Objects.requireNonNull(configs).getStorageType();
        DB mongoDB = null;
        try {
            mongoDB = configs.getMongoDB();
        } catch (UnknownHostException e) {
            log.error(e.getMessage());
        }
        EntityManager entityManager = configs.getEntityManager();
        SettingService settingService = new SettingService(type);

        RepositoryFactory repositoryFactory = new RepositoryFactoryImpl(settingService, entityManager, mongoDB);
        UserService userService = new LoggingUserDecorator(new UserServiceImpl(repositoryFactory));
        BookService bookService = new LoggingBookDecorator(new BookServiceImpl(repositoryFactory));
        bookServiceREST = new BookRESTController(bookService, userService);
        userServiceREST = new UserRESTController(userService);
        settings = new SettingRESTController(settingService);
    }
}
