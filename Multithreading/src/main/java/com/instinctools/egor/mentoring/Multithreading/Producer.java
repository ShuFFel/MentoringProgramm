package com.instinctools.egor.mentoring.Multithreading;

public class Producer implements Runnable {

    private SharedResource resource;

    public Producer(SharedResource resource){
        this.resource = resource;
    }

    public void run() {
        while (true) {
            if (resource.getCounter() < 10) {
                resource.increase();
            }
        }

    }
}
