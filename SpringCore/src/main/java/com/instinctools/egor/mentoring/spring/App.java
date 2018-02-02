package com.instinctools.egor.mentoring.spring;

import com.instinctools.egor.mentoring.spring.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(AppConfig.class);
    }
}
