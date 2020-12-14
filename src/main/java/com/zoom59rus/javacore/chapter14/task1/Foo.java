package main.java.com.zoom59rus.javacore.chapter14.task1;

import java.util.concurrent.Semaphore;

public class Foo {
    Semaphore semaphoreA = new Semaphore(0);
    Semaphore semaphoreB = new Semaphore(0);

    synchronized public void first() {
        System.out.print("first");
        semaphoreA.release();
    }

    synchronized public void second() {
        try {
            semaphoreA.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("second");
        semaphoreB.release();
    }

    synchronized public void third() {
        try {
            semaphoreB.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("third");
    }
}