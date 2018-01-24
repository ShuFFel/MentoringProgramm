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
import com.instinctools.egor.mentoring.web.ui.BookWorkingMenu;
import com.instinctools.egor.mentoring.web.ui.UserWorkingMenu;
import com.mongodb.DB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Objects;

public class Starter {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        Configs configs = null;
        try {
            configs = new Configs("application.properties");
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        StorageType type = Objects.requireNonNull(configs).getStorageType();
        DB mongoDB = null;
        try {
            mongoDB = configs.getMongoDB();
        } catch (UnknownHostException e) {
            LOGGER.error(e.getMessage());
        }
        EntityManager entityManager = configs.getEntityManager();
        SettingService settingService = new SettingService(type);

        RepositoryFactory repositoryFactory = new RepositoryFactoryImpl(settingService, entityManager, mongoDB);
        UserService userService = new LoggingUserDecorator(new UserServiceImpl(repositoryFactory));
        BookService bookService = new LoggingBookDecorator(new BookServiceImpl(repositoryFactory));
        BookWorkingMenu bookWorkingMenu = new BookWorkingMenu(bookService);
        UserWorkingMenu userWorkingMenu = new UserWorkingMenu(userService, bookWorkingMenu);
        userWorkingMenu.start();
    }
}
