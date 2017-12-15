package com.instinctools.egor.mentoring.classloader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Scanner;

public class ConsoleMenuWithLogger {

    Class<?> aClass = null;
    Logger log = LogManager.getLogger(ConsoleMenuWithLogger.class);
    public ConsoleMenuWithLogger() {
        while (true){
            log("Menu: \n1-start \n0-exit \n");
            Scanner scanner = new Scanner(System.in);
            Integer chose = scanner.nextInt();
            if(chose == 1) {
                log("Input full path to jar: ");
                String path = scanner.next();
                log("Input jar name: ");
                String jarName = scanner.next();
                log("Input full Class name: ");
                String className = scanner.next();
                log("Input full Mehtod name: ");
                String methodName = scanner.next();
                try {
                   MyLoader loader = MyLoader.getInstance(path, jarName);
                   Class<?> aClass = loader.loadClass(className);
                   log("Here some Message from class: \n");
                   loader.getMessageFromClass(aClass, methodName);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else
            {
                break;
            }
        }
    }
    private void log(String s){
        log.info(s);
    }
}
