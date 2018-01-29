package com.instinctools.egor.mentoring.spring;

import com.instinctools.egor.mentoring.spring.config.AppConfig;
import com.instinctools.egor.mentoring.spring.core.entity.Book;
import com.instinctools.egor.mentoring.spring.core.entity.User;
import com.instinctools.egor.mentoring.spring.services.BookService;
import com.instinctools.egor.mentoring.spring.services.UserService;
import com.instinctools.egor.mentoring.spring.services.decorators.bookdecorator.LoggingBookDecorator;
import com.instinctools.egor.mentoring.spring.services.decorators.userdecorator.LoggingUserDecorator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    }
}
