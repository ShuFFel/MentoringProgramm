package com.instinctools.egor.mentoring.classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class MyLoader extends URLClassLoader{

    private MyLoader(URL[] urls) {
        super(urls);
    }

    public static MyLoader getInstance(String pathToJar, String jarName) throws Exception {
        String url = "file://"+pathToJar+"/" + jarName;
        URL[] urls = {new URL(url)};
        MyLoader myLoader = new MyLoader(urls);
        return myLoader;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }

    public String getMessageFromClass(Class<?> aClass, String methodName) throws Exception {
        Constructor<?> constructor = aClass.getConstructor();
        Object obj = constructor.newInstance();
        Method method = aClass.getMethod("Say");
        String res = method.invoke(obj).toString();
        return res;
    }
}
