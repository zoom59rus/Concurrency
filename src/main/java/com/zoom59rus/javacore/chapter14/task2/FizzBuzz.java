package main.java.com.zoom59rus.javacore.chapter14.task2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

public class FizzBuzz {
    private BlockingQueue<Integer> numbers;
    private CountDownLatch cdl;
    private AtomicBoolean a;
    private AtomicBoolean b;

    private Semaphore semaphoreA = new Semaphore(1);
    private Semaphore semaphoreB = new Semaphore(0);
    private Semaphore semaphoreC = new Semaphore(0);
    private Semaphore semaphoreD = new Semaphore(0);

    public FizzBuzz(int n, CountDownLatch cdl) {
        this.numbers = new ArrayBlockingQueue<>(n);
        for (int i = 1; i <= n; ) {
            numbers.add(i++);
        }
        this.a = new AtomicBoolean(false);
        this.b = new AtomicBoolean(false);
        this.cdl = cdl;
    }

    public void fizz() {
        try {
            semaphoreA.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(a.get() && !b.get()){
            if(numbers.size() == 1){
                System.out.print("fizz");
                return;
            }else System.out.print("fizz, ");

            try {
                numbers.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            a.set(false);
            b.set(false);
            semaphoreB.release();
            fizz();
        }

        if(numbers.peek() % 3 == 0){
            a = new AtomicBoolean(true);
        }else {
            a = new AtomicBoolean(false);
        }
        semaphoreB.release();
        fizz();
    }


    public void buzz() {
        try {
            semaphoreB.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(numbers.peek() % 5 == 0){
            b = new AtomicBoolean(true);
            if(!a.get()){
                if(numbers.size() == 1){
                    System.out.print("buzz");
                    return;
                }else System.out.print("buzz, ");
                try {
                    numbers.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a.set(false);
                b.set(false);
                semaphoreA.release();
                buzz();
            }
            semaphoreC.release();
            buzz();
        }else if (!a.get()){
            semaphoreD.release();
            buzz();
        }else {
            semaphoreA.release();
            buzz();
        }
    }

    public void fizzbuzz() {
        try {
            semaphoreC.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(numbers.size() == 1){
            System.out.print("fizzbuzz");
            return;
        }else System.out.print("fizzbuzz, ");
        try {
            numbers.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a.set(false);
        b.set(false);
        semaphoreA.release();
        fizzbuzz();
    }

    public void number() {
        try {
            semaphoreD.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            if(numbers.size() == 1){
                System.out.print(numbers.take());
                return;
            }else System.out.print(numbers.take() + ", ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a.set(false);
        b.set(false);
        semaphoreA.release();
        number();
    }
}
