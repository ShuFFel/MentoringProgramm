package com.instinctools.egor.mentoring.Multithreading;

public class Consumer implements Runnable{

    private SharedResource resource;

    public Consumer(SharedResource resource) {
        this.resource = resource;
    }

    public void run() {
        while (true) {
            if (resource.getCounter() > 5) {
                resource.decrease();
            }
        }

    }
}
