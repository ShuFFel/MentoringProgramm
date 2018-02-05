package com.instinctools.egor.mentoring.spring;

import com.instinctools.egor.mentoring.spring.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public final class App {
    private App() {
    }

    public static void main(final String[] args) {
        new AnnotationConfigApplicationContext(AppConfig.class);
    }
}
