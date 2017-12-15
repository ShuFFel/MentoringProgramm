package com.instinctools.egor.mentoring.Multithreading;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class SharedResource {

    private final Lock sourceLock = new ReentrantLock();
    private static final SharedResource istance = new SharedResource();
    private int counter = 10;
    private Logger log = LogManager.getLogger(SharedResource.class);

    public void increase(){
        sourceLock.lock();
        try{
            counter++;
            log("Source was increased \nSource: "+counter);
        }
        finally {
            sourceLock.unlock();
        }     
    }

    public void decrease(){
        sourceLock.lock();
        try{
            counter--;
            log("Source was decreased \nSource: "+counter);
        }
        finally {
            sourceLock.unlock();
        }
    }

    public synchronized int getCounter() {
        return counter;
    }

    public static SharedResource getIstance() {
        return istance;
    }
    private void log(String s){
        log.info(s);
    }

}



