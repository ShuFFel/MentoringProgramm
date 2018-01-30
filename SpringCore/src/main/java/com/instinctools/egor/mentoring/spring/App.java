package com.instinctools.egor.mentoring.spring;

import com.instinctools.egor.mentoring.spring.config.AppConfig;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(AppConfig.class).getBeanFactory();
    }
}
