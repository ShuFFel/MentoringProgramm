package com.instinctools.egor.mentoring.Multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Producer producer = new Producer(SharedResource.getIstance());
        Consumer consumer = new Consumer(SharedResource.getIstance());
        executorService.execute(consumer);
        executorService.execute(producer);
    }
}
