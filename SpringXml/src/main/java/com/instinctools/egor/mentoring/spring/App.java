package com.instinctools.egor.mentoring.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("mainBeanConf.xml");
    }
}
